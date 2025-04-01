from flask import Flask, jsonify, request
from flask_cors import CORS
import time

app = Flask(__name__)
CORS(app)

# Заготовленные вопросы
QUESTIONS = [
    {
        "id": 1,
        "text": "Указано ли в Вашем договоре на обязанность Правообладателя обеспечить оформление лицензий в установленном порядке?",
        "type": "single-choice",
        "explanation": "Обычно это условие содержится в пункте «Права и обязанности сторон»",
        "options": [
            {"id": "a", "text": "Прописана обязанность Правообладателя обеспечить оформление лицензий в установленном порядке "},
            {"id": "b", "text": "Такое условие прописано, но указано, что расходы по оформлению лицензий возлагаются на Пользователя "},
            {"id": "c", "text": "Такое условие не прописано "},
        ],
    },
    {
        "id": 2,
        "text": "Указано ли в Вашем договоре на обязанность Правообладателя консультировать Пользователя по вопросам деятельности франшизы?",
        "type": "single-choice",
        "explanation": "Обычно это условие содержится в пункте «Права и обязанности сторон»",
        "options": [
            {"id": "a", "text": "Прописана обязанность Правообладателя консультировать Пользователя по широкому кругу вопросов",
             "explanation": "Примеры направлений консультирования: выбор и обустройство помещения, планирование пространства, управление и функционирование предприятия, техническое обслуживание оборудования, обучение персонала, дизайн и оформление, финансовые и кадровые вопросы, лицензирование и сертификация, а также иные аспекты, связанные с системой франшизы."},
            {"id": "b", "text": "Прописана обязанность Правообладателя консультировать Пользователя, но не указано, по каким именно вопросам"},
            {"id": "c", "text": "Не прописана обязанность Правообладателя консультировать Пользователя"},
        ],
    },
]


@app.route('/api/questions', methods=['GET'])
def get_questions():
    """Возвращает список вопросов."""
    return jsonify({"success": True, "data": QUESTIONS}), 200


@app.route('/api/submit-answers', methods=['POST'])
def submit_answers():
    """Принимает ответы пользователя и возвращает результаты."""
    try:
        # Получение данных из запроса
        answers = request.json
        print("Полученные ответы:", answers)

        results = {
            'success': True,
            'data': []
        }

        risk_scores = []

        for question in QUESTIONS:
            answer = answers.get(str(question['id']))

            if question['id'] == 1 and answer == 'b':
                results['data'].append("Оформление лицензий влечет возникновение финансовых и временных издержек")
                risk_scores.append(2)
            elif question['id'] == 1 and answer == 'c':
                results['data'].append(
                    "Отсутствие надлежащим образом оформленных лицензий влечет риск привлечения Вас к юридической ответственности")
                risk_scores.append(3)
            else:
                risk_scores.append(1)  # Ответ "a" (низкий риск)

            if question['id'] == 2 and answer == 'b':
                results['data'].append(
                    "Если в договоре не указаны конкретные направления консультаций, между Пользователем и Правообладателем могут возникнуть недопонимания и разногласия. Лучше дополнительно согласовать эти вопросы с Правообладателем")
                risk_scores.append(2)
            elif question['id'] == 2 and answer == 'c':
                results['data'].append(
                    "Если в договоре не прописана обязанность Правообладателя консультировать Пользователя, у Вас могут возникнуть организационные, финансовые и иные трудности при ведении бизнеса. Лучше дополнительно согласовать эти вопросы с Правообладателем")
                risk_scores.append(3)
            else:
                risk_scores.append(1)  # Ответ "a" (низкий риск)

            # Рассчитываем средний уровень риска
        avg_risk = sum(risk_scores) / len(risk_scores)

        if avg_risk <= 1.5:
            results['data'] = [("Общий уровень риска: Низкий")] + results['data']
        elif avg_risk <= 2.5:
            results['data'] = [("Общий уровень риска: Средний")] + results['data']
        else:
            results['data'] = [("Общий уровень риска: Высокий")] + results['data']

        return jsonify(results), 200
    except Exception as e:
        return jsonify({"success": False, "error": str(e)}), 500


@app.route('/api/check-franchisor', methods=['POST'])
def check_franshisor():
    answers = request.json
    time.sleep(2)
    return jsonify({"success": True,
                    "data": {
                        'name': answers['name'],
                        'location': 'Moscow',
                        'status': 'active'
                    }}), 200


@app.route('/api/heartbeat', methods=['POST'])
def heartbeat():
    data = request.json
    print("Получен heartbeat:", data)
    # Здесь можно сохранить данные в базу или обработать их
    return jsonify({"success": True}), 200


if __name__ == '__main__':
    app.run(debug=True)
