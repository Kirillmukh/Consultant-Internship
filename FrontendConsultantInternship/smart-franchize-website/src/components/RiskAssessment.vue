<template>
  <div class="risk-assessment">
    <h1>Вопрос {{ currentQuestionIndex + 1 }}</h1>

    <!-- Текущий вопрос -->
    <div v-if="currentQuestion">
      <p>
        {{ currentQuestion.text }}
        <span v-if="currentQuestion.hint" class="tooltip" :title="currentQuestion.hint">
          ❓
        </span>
      </p>
      <div v-for="option in currentQuestion.options" :key="option.id" class="option">
        <label>
          <input type="radio" :name="'question-' + currentQuestion.id" :value="option.id"
            v-model="answers[currentQuestion.id]" @change="saveProgress" />
          <span v-html="option.text"></span>
          <span v-if="option.hint" class="tooltip" :title="option.hint">
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
    <button v-if="currentQuestionIndex === questions.length - 1" @click="submitAnswers"
      :disabled="Object.keys(answers).length !== questions.length">
      Отправить ответы
    </button>

    <!-- Кнопка сброса -->
    <button class="reset-button" @click="resetProgress">Сбросить</button>


    <!-- Прогресс-бар -->
    <div class="progress-bar">
      <div v-for="(question, index) in questions" :key="question.id" class="progress-item"
        :class="{ active: currentQuestionIndex === index, completed: answers[question.id] !== undefined }"
        @click="goToQuestion(index)" :title="question.text"></div>
    </div>
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
      try {
        // Запрос к API для получения списка вопросов
        const response = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/v1/questions`);
        const data = await response.json();

        if (data.success) {
          console.log("Вопросы:", data);
          // Успешно получили вопросы
          this.questions = data.data;
        } else {
          throw new Error("Не удалось загрузить вопросы");
        }
      } catch (error) {
        console.error("Ошибка загрузки вопросов:", error);
        alert("Не удалось загрузить вопросы. Попробуйте позже.");
      }
      this.loadProgress();
    },
    nextQuestion() {
      if (this.currentQuestionIndex < this.questions.length - 1) {
        this.currentQuestionIndex++;
        this.saveProgress();
      }
    },
    prevQuestion() {
      if (this.currentQuestionIndex > 0) {
        this.currentQuestionIndex--;
        this.saveProgress();
      }
    },
    goToQuestion(index) {
      this.currentQuestionIndex = index;
      this.saveProgress();
    },

    submitAnswers() {
      // Переход на страницу результатов
      this.$router.push({ name: "results" });
    },
    saveProgress() {
      // Сохранение текущего прогресса в localStorage
      localStorage.setItem("riskAssessmentAnswers", JSON.stringify(this.answers));
      localStorage.setItem("riskAssessmentCurrentQuestion", this.currentQuestionIndex);
    },
    loadProgress() {
      // Загрузка прогресса из localStorage
      const savedAnswers = localStorage.getItem("riskAssessmentAnswers");
      const savedQuestionIndex = localStorage.getItem("riskAssessmentCurrentQuestion");

      if (savedAnswers) {
        this.answers = JSON.parse(savedAnswers);
      }
      if (savedQuestionIndex) {
        this.currentQuestionIndex = parseInt(savedQuestionIndex, 10);
      }
    },
    resetProgress() {
      // Сброс прогресса
      // this.answers = {};
      // this.currentQuestionIndex = 0;
      localStorage.removeItem("riskAssessmentAnswers");
      localStorage.removeItem("riskAssessmentCurrentQuestion");
      this.$router.push({ name: "home" });
    },
  },
  mounted() {
    this.fetchQuestions();
  },
};
</script>

<style scoped>
.risk-assessment {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 20px;
  background-color: #EEE2D4;
  border: 1px solid #ccc;
}

h1 {
  font-family: 'Tektur';
  font-size: 40px;
  font-weight: 400;
}

.progress-bar {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
  margin-top: 20px;
}

.progress-item {
  width: 60px;
  height: 4px;
  margin: 0 5px;
  border-radius: 2px;
  background-color: #D9C4AB;
  cursor: pointer;
  transition: background-color 0.3s, border-color 0.3s;
}

.progress-item.active {
  background-color: #007bff;
}

.progress-item.completed {
  background-color: #91582F;
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

.reset-button {
  margin-top: 20px;
  background-color: #dc3545;
}

.reset-button:hover {
  background-color: #c82333;
}
</style>