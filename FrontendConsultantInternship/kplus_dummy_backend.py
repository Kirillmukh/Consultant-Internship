from flask import Flask, jsonify, request
from flask_cors import CORS
import time

app = Flask(__name__)
CORS(app)

# Заготовленные вопросы
QUESTIONS = [
    {
        "id": 1,
        "text": "Указано ли в Вашем договоре условие об изменении вознаграждения правообладателю?",
        "hint": '''Пример формулировки этого условия:
Правообладатель имеет право увеличить вознаграждение в одностороннем порядке, уведомив Пользователя заблаговременно в соответствии с обычаями делового оборота и изменением цен на рынке идентичных услуг;
Правообладатель вправе изменять размер Периодических платежей в течение срока действия настоящего Договора, но не чаще одного раза в год; В случае если Правообладатель изменил размер Периодических платежей на следующий календарный год, он обязан сообщить об этом Пользователю не позднее 1 декабря текущего года.
''',
        "type": "single-choice",
        "options": [
            {
                "id": "c",
                "text": "Указано",
                "risk_level": "Высокий",
                "explanation": "Данное условие может повлечь незапланированные расходы"
            },
            {
                "id": "a",
                "text": "Не указано",
                "risk_level": "Низкий"
            }
        ]
    },
    {
        "id": 2,
        "text": "Указана ли в Вашем договоре обязанность согласовывать с правообладателем (франчайзером) ценовую политику ведения бизнеса?",
        "type": "single-choice",
        "options": [
            {
                "id": "с",
                "text": "Указано (примерная формулировка: правообладатель дает указания, готовит и координирует мероприятия по стимулированию сбыта Товара в соответствии с действующей у Правообладателя системой скидок и дисконтировании. Правообладатель устанавливает ценовую политику в отношении Товаров. Пользователь следует единой системе ценообразования, применяет систему льгот, скидок и дисконтов, а также участвует в иных дисконтных акциях, организованных и применяемых Правообладателем)",
                "risk_level": "Высокий",
                "explanation": "К сожалению, Вы не сможете самостоятельно устанавливать цены, которые захотите и должны будете действовать в соответствии с указаниями правообладателя (франчайзера). Возможно, Вам даже придется работать в убыток либо выходить в “ноль” без получения прибыли"
            },
            {
                "id": "a",
                "text": '''Не указано (либо формулировка в следующих вариациях: Франчайзер (правообладатель) не имеет права определять отпускные цены на продукцию (услуги), реализуемую (оказываемые) Франчайзи своим клиентам, а также устанавливать верхний или нижний пределы этих цен. Указания об уровне отпускных цен на продукцию могут даваться Франчайзором только в качестве рекомендаций;<br>
Пользователь имеет право: самостоятельно устанавливать и утверждать розничные цены на Продукцию и Дополнительную продукцию;<br>
Пользователь вправе самостоятельно разрабатывать и проводить собственные акции и спецпредложения. Указанные мероприятия (их содержание и виды применяемых в процессе их проведения информационных и (или) рекламных материалов) должны быть письменно согласованы с Правообладателем в срок не позднее N дней до момента их введения в действие<br>
                ''',
                "risk_level": "Низкий",
                "explanation": "Вы не ограничены в ценообразовании и можете сами выставлять и  корректировать цены по своему усмотрению."
            }
        ]
    },
    {
        "id": 3,
        "text": "Какой суд для разрешения споров указан в Вашем договоре?",
        "type": "single-choice",
        "options": [
            {
                "id": "a",
                "text": "Указан суд по Вашему местоположению",
                "risk_level": "Низкий",
                "explanation": "В таком случае Вам будет удобнее и менее затратно разрешать споры в суде."
            },
            {
                "id": "b",
                "text": "Указан суд по местоположению правообладателя",
                "risk_level": "Средний",
                "explanation": "Это может повлечь дополнительные сложности и расходы в связи с разрешением спора в другом регионе."
            }
        ]
    },
    {
        "id": 4,
        "text": "Указано ли в Вашем договоре условие о досудебном (претензионном) порядке разрешения споров?",
        "hint": 'обычно это условия содержится в разделе “Порядок разрешения споров”',
        "type": "single-choice",
        "options": [
            {
                "id": "a",
                "text": "Указано",
                "risk_level": "Низкий",
                "explanation": '''Досудебное урегулирование имеет безусловные преимущества:<br>
- экономия времени и нервов. Разбирательство в суде может затянуться на месяцы (а то и годы), и способно причинить немало беспокойства;<br>
- экономия средств. Решение вопросов в суде неизбежно связано с судебными издержками;<br>
- возможность сохранить партнерские отношения. К сожалению, решение спора в суде часто приводит к тому, что стороны судебного конфликта расстаются врагами, и дальнейшее совместное ведение бизнеса становится невозможным.
'''
            },
            {
                "id": "b",
                "text": "Не указано",
                "risk_level": "Средний",
                "explanation": '''Если Вы не включите условие о претензионном порядке, то на разрешение арбитражного суда можно передать (<a href='https://login.consultant.ru/link/?req=doc&base=LAW&n=495133&dst=1497' target='_blank'>ч. 5 ст. 4 АПК РФ</a>):<br>
                1) спор о взыскании денежных средств - только по истечении 30 календарных дней со дня направления претензии, если иные срок и (или) порядок не установлены законом;<br>
                2) иные возникшие из договора споры - без соблюдения досудебного порядка, только если такой порядок не установлен федеральным законом.
                '''
            }
        ]
    },
    {
        "id": 5,
        "text": "Указано ли в Вашем договоре условие о его пролонгации (продлении после истечения действия)?",
        "hint": "Обычно это условие указывается разделе “срок действия договора”",
        "type": "single-choice",
        "options": [
            {
                "id": "a",
                "text": "Указано",
                "hint": "Примерная формулировка: если во время действия Договора Пользователем не было допущено существенных нарушений его положений, которые могли бы привести к его досрочному прекращению; Пользователь добросовестно выполнял обязанности по Договору; Пользователь готов понести расходы, связанные с прохождением его персоналом повторных Обученческих курсов в месте и по программе, указанным Правообладателем",
                "risk_level": "Низкий",
                "explanation": "В таком случае Вы не потеряете вложенные деньги и сможете дальше работать под брендом даже после истечения срока договора."
            },
            {
                "id": "b",
                "text": "Не указано",
                "hint": 'Либо содержится формулировка наподобие такой: правообладатель вправе отказать в заключении договора коммерческой концессии на новый срок при условии, что в течение трех лет со дня истечения срока настоящего договора он не будет заключать с другими лицами аналогичные договоры коммерческой концессии и соглашаться на заключение аналогичных договоров коммерческой субконцессии, действие которых будет распространяться на ту же территорию, на которой действовал настоящий договор',
                "risk_level": "Средний",
                "explanation": "В таком случае по истечению срока действия договора Вы рискуете полностью потерять свой бизнес т.к. не сможете пользоваться товарным знаком, фирменным наименованием иными результатами интеллектуальной деятельности. Однако согласно <a href='https://login.consultant.ru/link/?req=doc&base=LAW&n=493202&dst=86' target='_blank'>ст. 1035 ГК РФ</a> у Вас возникает преимущественное право на пролонгацию этого договора в будущем. Если в течение года правообладатель (франчайзер) заключит договор с кем-то другим, Вы имеете право через суд потребовать перевода на себя прав и обязанностей по заключенному договору и возмещения убытков, причиненных отказом возобновить с ним договор коммерческой концессии, или только возмещения таких убытков."
            },
        ]
    },
    {
        "id": 6,
        "text": "Указано ли в Вашем договоре на обязанность Правообладателя консультировать Пользователя по вопросам деятельности франшизы?",
        "hint": 'Обычно это условие содержится в пункте «Права и обязанности сторон»',
        "type": "single-choice",
        "options": [
            {
                "id": "a",
                "text": "Прописана обязанность Правообладателя консультировать Пользователя по широкому кругу вопросов",
                "risk_level": "Низкий",
            },
            {
                "id": "b",
                "text": "Прописана обязанность Правообладателя консультировать Пользователя, но не указано, по каким именно вопросам",
                "risk_level": "Средний",
                "explanation": "Гражданским Кодексом в <a href='https://login.consultant.ru/link/?req=doc&base=LAW&n=493202&dst=18' target='_blank'>ст.1031 РФ</a> закреплена обязанность Правообладателя обеспечить проведение консультаций. Если в договоре не указаны конкретные направления консультаций, между Вами и Правообладателем могут возникнуть недопонимания и разногласия.",
                "note": '''Рекомендуем Вам дополнительно согласовать эти вопросы с Правообладателем. Если не представляется возможным внести соответствующее изменение в договор, советуем провести переговоры по заключению дополнительного соглашения. Если же в результате переговоров Вам не удалось достичь заключения дополнительного соглашения, советуем Вам составить протокол разногласий.<br>
(Подсказка):<br>
Примеры направлений консультирования:<br>
- по выбору Помещений (месторасположение и технические характеристики, определяющие возможность размещения Предприятия в помещении), и консультирование Пользователя по оптимальному выбору месторасположения и пригодности выбранного Пользователем Помещения для размещения Предприятия;<br>
- по планированию распределения площадей и расположению оборудования;<br>
- по управлению и функционированию Предприятия;<br>
- по эффективному использованию и техническому обслуживанию используемого на Предприятии оборудования;<br>
- по обучению персонала Предприятия;<br>
- по оформлению (дизайну) Предприятия, дизайну и расстановке мебели и декоративным элементам;<br>
- по вопросам финансов, управления, кадров и кадровой политики, администрирования и продвижения Продукции, направленное на ведение деятельности Предприятия наиболее эффективным способом;<br>
- по вопросам лицензирования деятельности Предприятия и сертификации Продукции;<br>
- по дополнительно возникающим вопросам, имеющим непосредственное отношение к Системе и т.д.
'''
            },
            {
                "id": "c",
                "text": "Не прописана обязанность Правообладателя консультировать Пользователя",
                "risk_level": "Высокий",
                "explanation": '''Гражданским Кодексом в <a href='https://login.consultant.ru/link/?req=doc&base=LAW&n=493202&dst=18' target='_blank'>ст.1031 РФ</a> закреплена обязанность Правообладателя обеспечить проведение консультаций. Если в договоре не указано на такую обязанность, у Вас могут возникнуть организационные, финансовые и иные трудности при ведении бизнеса.''',
                "note": '''Рекомендуем Вам дополнительно согласовать эти вопросы с Правообладателем. Если не представляется возможным внести соответствующее изменение в договор, советуем провести переговоры по заключению дополнительного соглашения. Если же в результате переговоров Вам не удалось достичь заключения дополнительного соглашения, советуем Вам составить протокол разногласий.<br>
(Подсказка):<br>
Примеры направлений консультирования:<br>
- по выбору Помещений (месторасположение и технические характеристики, определяющие возможность размещения Предприятия в помещении), и консультирование Пользователя по оптимальному выбору месторасположения и пригодности выбранного Пользователем Помещения для размещения Предприятия;<br>
- по планированию распределения площадей и расположению оборудования;<br>
- по управлению и функционированию Предприятия;<br>
- по эффективному использованию и техническому обслуживанию используемого на Предприятии оборудования;<br>
- по обучению персонала Предприятия;<br>
- по оформлению (дизайну) Предприятия, дизайну и расстановке мебели и декоративным элементам;<br>
- по вопросам финансов, управления, кадров и кадровой политики, администрирования и продвижения Продукции, направленное на ведение деятельности Предприятия наиболее эффективным способом;<br>
- по вопросам лицензирования деятельности Предприятия и сертификации Продукции;<br>
- по дополнительно возникающим вопросам, имеющим непосредственное отношение к Системе и т.д.
'''
            }
        ]
    }
]


@app.route('/api/questions', methods=['GET'])
def get_questions():
    """Возвращает список вопросов."""
    return jsonify({"success": True, "data": QUESTIONS}), 200


@app.route('/api/submit-answers', methods=['POST'])
def submit_answers():
    """Принимает ответы пользователя и возвращает результаты в формате, предложенном юристами."""
    try:
        # Получение данных из запроса
        answers = request.json
        print("Полученные ответы:", answers)

        # Инициализация результата
        results = {
            'success': True,
            'low_count': 0,
            'moderate_count': 0,
            'high_count': 0,
            'data': []
        }

        # Обработка каждого вопроса
        for question in QUESTIONS:
            question_id = str(question['id'])
            answer_id = answers.get(question_id)

            if not answer_id:
                continue

            # Найти выбранный вариант ответа
            selected_option = next((option for option in question['options'] if option['id'] == answer_id), None)

            if not selected_option:
                continue

            # Формирование результата для текущего вопроса
            question_result = {
                'question': question['text'],  # Текст вопроса
                'answer': selected_option['text'],  # Текст ответа пользователя
                'risk_level': selected_option['risk_level'],  # Уровень риска
                'explanation': selected_option.get('explanation', ''),  # Объяснение
                'note': selected_option.get('note', ''),  # Примечание
            }

            # Определение уровня риска
            if answer_id == 'a':
                question_result['risk_level'] = 'Низкий'
                results['low_count'] += 1
            elif answer_id == 'b':
                question_result['risk_level'] = 'Средний'
                results['moderate_count'] += 1
            elif answer_id == 'c':
                question_result['risk_level'] = 'Высокий'
                results['high_count'] += 1

            # Добавление результата в общий список
            results['data'].append(question_result)

        return jsonify(results), 200

    except Exception as e:
        print("Ошибка:", e)
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
