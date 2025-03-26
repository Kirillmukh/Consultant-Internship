import os
from flask import Flask, request, jsonify, Response
import textract
from sentence_transformers import SentenceTransformer, util


app = Flask(__name__)

# Глобальная конфигурация: путь к файлу с рисковыми предложениями
RISK_SENTENCES_PATH = 'risks.txt'


def load_risk_sentences(filepath):
    """
    Загружает список рисковых предложений из файла.
    Каждая строка файла считается отдельным рисковым предложением.
    """
    if not os.path.exists(filepath):
        raise FileNotFoundError(f"Файл {filepath} не найден.")
    with open(filepath, 'r', encoding='utf-8') as file:
        risks = [line.strip() for line in file if line.strip()]
    return risks


# Загружаем рисковые предложения при старте сервиса
try:
    RISK_SENTENCES = load_risk_sentences(RISK_SENTENCES_PATH)
except Exception as e:
    RISK_SENTENCES = []
    print("Ошибка загрузки рисковых предложений:", e)

# Инициализация модели SentenceTransformer
MODEL = SentenceTransformer('paraphrase-multilingual-MiniLM-L12-v2')


def extract_text_from_file(file_path):
    """
    Извлекает текст из файла (поддерживаются .docx и .pdf) с помощью textract.
    """
    try:
        print(file_path)
        if file_path.endswith('.pdf'):
            text_bytes = textract.process(file_path, method='pdfminer', language='ru')
        else:
            text_bytes = textract.process(file_path, language='ru')
        print('2')
        text = text_bytes.decode('utf-8')
        return text
    except Exception as e:
        print("Ошибка извлечения текста:", e)
        return None


def split_text_into_paragraphs(text):
    """
    Разбивает текст на абзацы. В данном случае используется разделение по двойному переносу строки.
    Если формат документа иной, можно модифицировать данную функцию.
    """
    paragraphs = [p.strip() for p in text.split('\n\n') if p.strip()]
    return paragraphs


def find_best_matching_paragraphs(paragraphs, risk_sentences):
    """
    Для каждого рискового предложения вычисляет наиболее похожий абзац из списка.
    Возвращает список словарей с текстом риска, найденным абзацем и уровнем уверенности (косинусное сходство).
    """
    # Предварительное вычисление эмбеддингов абзацев и рисковых предложений
    paragraph_embeddings = MODEL.encode(paragraphs, convert_to_tensor=True)
    risk_embeddings = MODEL.encode(risk_sentences, convert_to_tensor=True)

    results = []
    for i, risk in enumerate(risk_sentences):
        # Вычисляем косинусное сходство между эмбеддингом рискового предложения и всеми абзацами
        cosine_scores = util.cos_sim(risk_embeddings[i], paragraph_embeddings)  # [1, N] тензор
        best_idx = int(cosine_scores.argmax())
        best_score = float(cosine_scores[0][best_idx])

        results.append({
            'risk_sentence': risk,
            'matching_paragraph': paragraphs[best_idx],
            'confidence': best_score
        })
    return results


@app.route('/upload', methods=['POST'])
def upload_file():
    """
    Точка входа для загрузки файла.
    Ожидается файл с ключом 'file' в multipart/form-data запросе.
    После извлечения текста производится поиск подходящих абзацев для каждого рискового предложения.
    Результаты возвращаются в формате JSON.
    """
    if 'file' not in request.files:
        return jsonify({'error': 'В запросе отсутствует файл.'}), 400

    file = request.files['file']
    if file.filename == '':
        return jsonify({'error': 'Файл не выбран.'}), 400

    # Сохраняем файл во временную директорию
    os.makedirs('uploads', exist_ok=True)
    file_path = os.path.join('uploads', file.filename)
    file.save(file_path)

    # Извлекаем текст с помощью textract
    text = extract_text_from_file(file_path)
    if text is None:
        return jsonify({'error': 'Не удалось извлечь текст из файла.'}), 500

    # Разбиваем текст на абзацы (можно модифицировать под более сложное извлечение)
    paragraphs = split_text_into_paragraphs(text)
    if not paragraphs:
        return jsonify({'error': 'Не найдено абзацев в извлечённом тексте.'}), 500

    # Находим для каждого рискового предложения наиболее подходящий абзац
    results = find_best_matching_paragraphs(paragraphs, RISK_SENTENCES)

    # Удаляем временный файл
    os.remove(file_path)

    return jsonify({'results': results})


@app.route('/risks.txt')
def read_risks():
    with open("risks.txt", "r", encoding="utf-8") as f:
        content = f.read()
    return Response(content, mimetype="text/plain")


@app.route('/app.js')
def read_app_js():
    with open("app.js", "r", encoding="utf-8") as f:
        content = f.read()
    return Response(content, mimetype="text/plain")


@app.route('/')
def main_page():
    with open('page2.html', 'r', encoding='utf-8') as f:
        content = f.read()
    return content


if __name__ == '__main__':
    # Запуск сервера (в режиме отладки для разработки)
    app.run(debug=True)
