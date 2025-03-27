from flask import Flask, jsonify, request
from flask_cors import CORS
import time

app = Flask(__name__)
CORS(app)

# Заготовленные вопросы
QUESTIONS = [
    {
        "id": 1,
        "text": "Какова продолжительность договора франчайзинга?",
        "type": "single-choice",
        "explanation": "Продолжительность договора определяет срок, на который вы заключаете соглашение с франчайзером.",
        "options": [
            {"id": "a", "text": "1 год"},
            {"id": "b", "text": "3 года"},
            {"id": "c", "text": "5 лет"},
        ],
    },
    {
        "id": 2,
        "text": "Какие платежи предусмотрены договором?",
        "type": "single-choice",
        "explanation": "Платежи могут включать паушальный взнос, роялти и другие сборы.",
        "options": [
            {"id": "a", "text": "Только паушальный взнос", "explanation": "Единовременный платеж за право использования франшизы."},
            {"id": "b", "text": "Паушальный взнос и роялти", "explanation": "Роялти — это регулярные платежи за использование бренда."},
            {"id": "c", "text": "Паушальный взнос, роялти и маркетинговые сборы", "explanation": "Дополнительные сборы могут включать расходы на рекламу."},
        ],
    },
]

# Заготовленные результаты
RESULTS = {
    "success": True,
    "data": [
        "Ваш договор франчайзинга имеет стандартный срок действия.",
        "Платежи включают паушальный взнос, роялти и маркетинговые сборы.",
        "Риски минимальны, но рекомендуется проконсультироваться с юристом.",
    ],
}


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

        # Проверка ответов
        for question in QUESTIONS:
            answer = answers.get(str(question['id']))
            if answer:
                results['data'].append(f"Вопрос {question['id']}: {answer}")
                if question['id'] == 1 and answer == 'b':
                    results['data'].append("Ответ на вопрос 1 верный!")
                if question['id'] == 2 and answer == 'c':
                    results['data'].append("Ответ на вопрос 2 верный!")

        # Возвращаем заранее заготовленные результаты
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
