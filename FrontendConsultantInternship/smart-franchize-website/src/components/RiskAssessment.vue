<template>
  <div class="risk-assessment">
    <h1 v-if="loading">Вопросы загружаются...</h1>
    <!--<h1 v-else>Вопрос {{ currentQuestionIndex + 1 }}</h1>-->
    <h1 v-else>{{ currentQuestion.title }}</h1>

    <!-- Текущий вопрос -->
    <div v-if="!loading && currentQuestion">
      <p class="question_body">
        {{ currentQuestion.text }}
        <span v-if="currentQuestion.hint" class="tooltip" :title="currentQuestion.hint">
          ?
        </span>
      </p>

      <!-- Основные радиокнопки -->
      <div v-for="option in currentQuestion.answers" :key="option.id" class="option">
        <label>
          <input
            type="radio"
            :name="'question-' + currentQuestion.id"
            :value="option.id"
            v-model="answers[currentQuestion.id]"
            @change="onOptionChange(option)"
          />
          <span v-html="option.text"></span>
          <span v-if="option.hint" class="tooltip" :title="option.hint">?</span>
        </label>
        <!-- SubAnswers -->
        <div v-if="option.subAnswers && answers[currentQuestion.id] && answers[currentQuestion.id] == option.id" class="subanswers">
          <div v-for="subanswer in option.subAnswers" :key="subanswer.id" class="subanswer-option">
            <label>
              <input
                type="radio"
                :name="'subanswers-' + option.id"
                :value="subanswer.id"
                v-model="subAnswers[option.id]"
                @change="onOptionChange(subanswer)"
              />
              <span v-html="subanswer.text"></span>
              <span v-if="subanswer.hint" class="tooltip" :title="subanswer.hint">?</span>
            </label>
          </div>
        </div>
      </div>
    </div>

    <!-- Кнопки навигации -->
    <div v-if="!loading" class="navigation-buttons">
      <button @click="prevQuestion" :disabled="currentQuestionIndex === 0">Назад</button>
      <button
        v-if="currentQuestionIndex === questions.length - 1"
        @click="submitAnswers"
        :disabled="!areAllQuestionsAnswered"
      >
        К результатам
      </button>
      <button v-else @click="nextQuestion">Вперёд</button>
    </div>

    <!-- Прогресс-бар -->
    <div v-if="!loading" class="progress-bar">
      <div
        v-for="(question, index) in questions"
        :key="question.id"
        class="progress-item"
        :class="{ completed: isQuestionAnswered(question), active: currentQuestionIndex === index }"
        @click="goToQuestion(index)"
        :title="question.text"
      ></div>
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
      answers: {}, // Ответы на основные вопросы
      subAnswers: {}, // Ответы на подварианты
      loading: true, // Флаг загрузки
    };
  },
  computed: {
    currentQuestion() {
      return this.questions[this.currentQuestionIndex];
    },
    areAllQuestionsAnswered() {
      return this.questions.every((question) => this.isQuestionAnswered(question));
    },
  },
  methods: {
    async fetchQuestions() {
      try {
        const response = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/v1/questions`);
        const data = await response.json();
        if (data) {
          this.questions = data;
        } else {
          throw new Error("Не удалось загрузить вопросы");
        }
      } catch (error) {
        console.error("Ошибка загрузки вопросов:", error);
        alert("Не удалось загрузить вопросы. Попробуйте позже.");
      } finally {
        this.loading = false;
      }
      this.loadProgress();
    },
    onOptionChange(option) {
      console.log("Выбранный вариант:", option);
      this.saveProgress();
    },
    saveProgress() {
      localStorage.setItem("riskAssessmentAnswers", JSON.stringify(this.answers));
      localStorage.setItem("riskAssessmentSubAnswers", JSON.stringify(this.subAnswers));
      localStorage.setItem("riskAssessmentCurrentQuestion", this.currentQuestionIndex);
    },
    loadProgress() {
      const savedAnswers = localStorage.getItem("riskAssessmentAnswers");
      const savedSubAnswers = localStorage.getItem("riskAssessmentSubAnswers");
      const savedQuestionIndex = localStorage.getItem("riskAssessmentCurrentQuestion");

      if (savedAnswers) {
        this.answers = JSON.parse(savedAnswers);
      }
      if (savedSubAnswers) {
        this.subAnswers = JSON.parse(savedSubAnswers);
      }
      if (savedQuestionIndex) {
        this.currentQuestionIndex = parseInt(savedQuestionIndex, 10);
      }
    },
    isQuestionAnswered(question) {
      const selectedOptionId = this.answers[question.id];
      if (!selectedOptionId) {
        return false; // Если ничего не выбрано, вопрос не считается отвеченным
      }

      const selectedOption = question.answers.find(option => option.id === selectedOptionId);
      if (selectedOption && selectedOption.subAnswers) {
        // Если у выбранной опции есть subAnswers, проверяем, выбран ли subAnswer
        return !!this.subAnswers[selectedOption.id];
      }

      return true; // Если нет subAnswers, вопрос считается отвеченным
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
      console.log("Основные ответы:", this.answers);
      console.log("Ответы на подварианты:", this.subAnswers);
      this.$router.push({ name: "results" });
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

.question_body {
  font-size: 20px;
  font-weight: bold;
  text-align: left;
  padding-left: 50px;
  padding-right: 50px;
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


.progress-item.completed {
  background-color: #836645;
}

.progress-item.active {
  background-color: #FFFFFF;
}

.tooltip {
  cursor: default;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 20px;
  height: 20px;
  background: #836645;
  color: white;
  border-radius: 4px;
  font-size: 0.8em;

  vertical-align: super;
  margin-left: 4px;
  position: relative;
  top: -0.4em;
}

.tooltip:hover {
  background-color: #91582F;
}

.option {
  margin-bottom: 10px;
  color: black;
  text-align: left;
  /* Добавлено выравнивание текста по левому краю */
  padding-left: 50px;
  padding-right: 50px;
}

.option span {
  text-align: left;
  /* Выравнивание текста по левому краю */
}

.navigation-buttons {
  display: block;
  justify-content: space-between;
  margin-top: 20px;
}

button {
  margin: 20px;
  height: 35px;
  width: 200px;
  background-color: #836645;
  color: white;
  border: none;
  border-radius: 10px;
  font-family: 'Tektur';
  font-size: 18px;
  cursor: pointer;
}

button:hover {
  background-color: #91582F;
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

input[type="radio"] {
  appearance: none; /* Убираем стандартный стиль радиокнопки */
  width: 16px; /* Устанавливаем ширину */
  height: 16px; /* Устанавливаем высоту */
  border: 2px solid white; /* Белые границы */
  border-radius: 50%; /* Делаем радиокнопку круглой */
  background-color: white; /* Прозрачный фон */
  cursor: pointer; /* Указатель при наведении */
  display: inline-block;
}

input[type="radio"]:checked {
  background-color: #836645; /* Цвет заливки при выборе */
  border-color: #836645; /* Цвет границы при выборе */
}

input[type="radio"]:focus {
  outline: none; /* Убираем стандартный фокус */
  box-shadow: 0 0 4px rgba(255, 255, 255, 0.8); /* Добавляем эффект фокуса */
}

/* Стили для subAnswers */
.subanswers {
  padding-left: 10px;
}

.subanswer-option {
  margin-bottom: 10px;
}
</style>