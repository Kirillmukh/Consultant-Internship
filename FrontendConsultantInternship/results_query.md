# Документация по запросу результатов

## Эндпоинт

- **URL**: `/api/submit-answers`
- **Метод**: `POST`
- **Описание**: Отправляет ответы пользователя на сервер для анализа и получения результатов.

---

## Формат запроса

Запрос отправляется в формате JSON и содержит ответы пользователя на вопросы.

### Пример запроса:

```json
{
  "1": "a",
  "2": "b",
  "3": "c"
}
```

### Описание полей:
- **Ключ**: Уникальный идентификатор вопроса (строка или число).
- **Значение**: Выбранный пользователем вариант ответа (строка).

---

## Формат ответа

Ответ возвращается в формате JSON и содержит подробный отчет по результатам.

### Пример ответа:

```json
{
  "success": true,
  "low_count": 2,
  "moderate_count": 3,
  "high_count": 1,
  "data": [
    {
      "question": "Установлен ли в Вашем договоре срок его действия?",
      "answer": "Да, срок действия договора больше 5 лет",
      "risk_level": "Средний",
      "explanation": "Срок действия договора больше 5 лет снижает вероятность возникновения споров, но может потребовать дополнительных согласований при продлении.",
      "note": "Рекомендуется заранее обсудить условия продления договора."
    },
    {
      "question": "Можете ли Вы самостоятельно устанавливать цены?",
      "answer": "Нет, цены устанавливаются франчайзером",
      "risk_level": "Высокий",
      "explanation": "Невозможность самостоятельного ценообразования может привести к убыткам, если цены не соответствуют рыночным условиям."
    },
    {
      "question": "Включено ли в договор обязательство о консультациях?",
      "answer": "Да, но направления консультаций не указаны",
      "risk_level": "Средний",
      "explanation": "Отсутствие конкретных направлений консультаций может привести к недопониманиям между сторонами."
    }
  ]
}
```

### Описание полей:
- **`success`** (логическое): Указывает, что запрос выполнен успешно.
- **`low_count`** (число): Количество вопросов с низким уровнем риска.
- **`moderate_count`** (число): Количество вопросов с средним уровнем риска.
- **`high_count`** (число): Количество вопросов с высоким уровнем риска.
- **`data`** (массив объектов): Содержит подробную информацию по каждому вопросу.

#### Поля внутри `data`:
- **`id`** (число): Номер вопроса
- **`question`** (строка): Текст вопроса.
- **`answer`** (строка): Ответ пользователя.
- **`risk_level`** (строка): Уровень риска (`Низкий`, `Средний`, `Высокий`).
- **`explanation`** (HTML, опционально): Объяснение уровня риска.
- **`legal_basis`** (HTML, опционально): Правовое обоснование.
- **`note`** (HTML, опционально): Дополнительные рекомендации или предупреждения. Если отсутствует, поле не включается в объект.
- **`hint`** (строка, опционально): Всплывающая подсказка.

