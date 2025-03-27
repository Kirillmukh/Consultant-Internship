<template>
    <div class="risk-assessment">
      <h1>Risk Assessment</h1>
      <p>Answer the questions to evaluate your risks.</p>
  
      <!-- Прогресс-бар -->
      <div class="progress-bar">
        <div
          v-for="(question, index) in questions"
          :key="question.id"
          class="progress-item"
          :class="{ active: currentQuestionIndex === index, completed: index < currentQuestionIndex }"
          @click="goToQuestion(index)"
          :title="question.text"
        ></div>
      </div>
  
      <!-- Текущий вопрос -->
      <div v-if="currentQuestion">
        <p>
          {{ currentQuestion.text }}
          <span
            v-if="currentQuestion.explanation"
            class="tooltip"
            :title="currentQuestion.explanation"
          >
            ❓
          </span>
        </p>
        <div v-for="option in currentQuestion.options" :key="option.id" class="option">
          <label>
            <input
              type="radio"
              :name="'question-' + currentQuestion.id"
              :value="option.id"
              v-model="answers[currentQuestion.id]"
            />
            {{ option.text }}
            <span
              v-if="option.explanation"
              class="tooltip"
              :title="option.explanation"
            >
              ❓
            </span>
          </label>
        </div>
      </div>
  
      <!-- Кнопки навигации -->
      <div class="navigation-buttons">
        <button @click="prevQuestion" :disabled="currentQuestionIndex === 0">Назад</button>
        <button @click="nextQuestion" :disabled="currentQuestionIndex === questions.length - 1">
          Вперёд
        </button>
      </div>
  
      <!-- Кнопка отправки -->
      <button v-if="currentQuestionIndex === questions.length - 1" @click="submitAnswers">
        Отправить ответы
      </button>
    </div>
  </template>
  
  <script>
  export default {
    name: "RiskAssessment",
    data() {
      return {
        questions: [], // Список вопросов
        currentQuestionIndex: 0, // Индекс текущего вопроса
        answers: {}, // Ответы пользователя
      };
    },
    computed: {
      currentQuestion() {
        return this.questions[this.currentQuestionIndex];
      },
    },
    methods: {
      async fetchQuestions() {
        // Симуляция данных с сервера
        this.questions = [
          {
            id: 1,
            text: "Какова продолжительность договора франчайзинга?",
            type: "single-choice",
            explanation: "Продолжительность договора определяет срок, на который вы заключаете соглашение с франчайзером.",
            options: [
              {
                id: "a",
                text: "1 год"
              },
              {
                id: "b",
                text: "3 года"
              },
              {
                id: "c",
                text: "5 лет"
              },
            ],
          },
          {
            id: 2,
            text: "Какие платежи предусмотрены договором?",
            type: "single-choice",
            explanation: "Платежи могут включать паушальный взнос, роялти и другие сборы.",
            options: [
              {
                id: "a",
                text: "Только паушальный взнос",
                explanation: "Единовременный платеж за право использования франшизы.",
              },
              {
                id: "b",
                text: "Паушальный взнос и роялти",
                explanation: "Роялти — это регулярные платежи за использование бренда.",
              },
              {
                id: "c",
                text: "Паушальный взнос, роялти и маркетинговые сборы",
                explanation: "Дополнительные сборы могут включать расходы на рекламу.",
              },
            ],
          },
        ];
      },
      nextQuestion() {
        if (this.currentQuestionIndex < this.questions.length - 1) {
          this.currentQuestionIndex++;
        }
      },
      prevQuestion() {
        if (this.currentQuestionIndex > 0) {
          this.currentQuestionIndex--;
        }
      },
      goToQuestion(index) {
        this.currentQuestionIndex = index;
      },
      submitAnswers() {
        console.log(this.answers); // Отправка ответов на сервер
        alert("Ваши ответы отправлены!");
      },
    },
    mounted() {
      this.fetchQuestions();
    },
  };
  </script>
  
  <style scoped>
  .risk-assessment {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;
  }
  
  .progress-bar {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
  }
  
  .progress-item {
    width: 20px;
    height: 20px;
    margin: 0 5px;
    border: 2px solid #ccc;
    border-radius: 50%;
    background-color: white;
    cursor: pointer;
    transition: background-color 0.3s, border-color 0.3s;
  }
  
  .progress-item.active {
    border-color: #007bff;
    background-color: #007bff;
  }
  
  .progress-item.completed {
    border-color: #28a745;
    background-color: #28a745;
  }
  
  .tooltip {
    cursor: pointer;
    color: #007bff;
    text-decoration: underline;
  }
  
  .option {
    margin-bottom: 10px;
  }
  
  .navigation-buttons {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
  }
  
  button {
    padding: 10px 15px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  
  button:disabled {
    background-color: #ccc;
    cursor: not-allowed;
  }
  </style>