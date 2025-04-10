<template>
  <div class="risk-assessment">
    <h1 v-if="loading">Вопросы загружаются...</h1>
    <h1 v-else>Вопрос {{ currentQuestionIndex + 1 }}</h1>

    <!-- Текущий вопрос -->
    <div v-if="!loading && currentQuestion">
      <p class="question_body">
        {{ currentQuestion.text }}
        <span v-if="currentQuestion.hint" class="tooltip" :title="currentQuestion.hint">
          ?
        </span>
      </p>
      <div v-for="option in currentQuestion.answers" :key="option.id" class="option">
         <label>
           <input type="radio" :name="'question-' + currentQuestion.id" :value="option.id"
             v-model="answers[currentQuestion.id]" @change="saveProgress" />
             <span class="custom-radio-circle"></span>
           <span v-html="option.text"></span>
           <span v-if="option.hint" class="tooltip" :title="option.hint">
             ?
           </span>
         </label>
       </div>
    </div>

    <!-- Кнопки навигации -->
    <div v-if="!loading" class="navigation-buttons">
      <button @click="prevQuestion" :disabled="currentQuestionIndex === 0">Назад</button>
      <button v-if="currentQuestionIndex === questions.length - 1" @click="submitAnswers"
        :disabled="!areAllQuestionsAnswered">
        К результатам
      </button>
      <button v-else @click="nextQuestion">
        Вперёд
      </button>
    </div>

    <!-- Прогресс-бар -->
    <div v-if="!loading" class="progress-bar">
      <div v-for="(question, index) in questions" :key="question.id" class="progress-item"
        :class="{ completed: isQuestionAnswered(question), active: currentQuestionIndex === index }"
        @click="goToQuestion(index)" :title="question.text"></div>
    </div>
  </div>
</template>

<script>

export default {
  name: "RiskAssessment",
  components: {
  },
  data() {
    return {
      questions: [], // Список вопросов
      currentQuestionIndex: 0, // Индекс текущего вопроса
      answers: {}, // Ответы пользователя
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
    handleAnswerChange(option) {
      if (!option.subanswers) return;
      // Удаляем ответы на предыдущие subanswers, если пользователь выбрал другой ответ
      this.answers = Object.fromEntries(
        Object.entries(this.answers).filter(([key]) => !key.startsWith(`${option.id}-`))
      );
    },
    updateSubanswers(parentId, subanswers) {
      // Обновляем ответы на subanswers
      Object.keys(subanswers).forEach((key) => {
        this.answers[`${parentId}-${key}`] = subanswers[key];
      });
    },
    isQuestionAnswered(question) {
      if (!this.answers[question.id]) return false;
      const selectedOption = question.answers.find((opt) => opt.id === this.answers[question.id]);
      if (selectedOption && selectedOption.subanswers) {
        return selectedOption.subanswers.every((sub) =>
          this.isQuestionAnswered({ id: `${question.id}-${sub.id}`, answers: sub.answers })
        );
      }
      return true;
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
      this.$router.push({ name: "results" });
    },
    saveProgress() {
      localStorage.setItem("riskAssessmentAnswers", JSON.stringify(this.answers));
      localStorage.setItem("riskAssessmentCurrentQuestion", this.currentQuestionIndex);
    },
    loadProgress() {
      const savedAnswers = localStorage.getItem("riskAssessmentAnswers");
      const savedQuestionIndex = localStorage.getItem("riskAssessmentCurrentQuestion");

      if (savedAnswers) {
        this.answers = JSON.parse(savedAnswers);
      }
      if (savedQuestionIndex) {
        this.currentQuestionIndex = parseInt(savedQuestionIndex, 10);
      }
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
  cursor: pointer;
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

/* Скрываем стандартную радиокнопку */
input[type="radio"] {
  display: none;
}

/* Стили для кастомной радиокнопки */
.custom-radio-circle {
  display: inline-block;
  width: 20px;
  height: 20px;
  border: 2px solid #836645;
  border-radius: 50%;
  position: relative;
  margin-right: 10px;
  cursor: pointer;
}

input[type="radio"]:checked + .custom-radio-circle {
  background-color: #836645;
}

.custom-radio-circle::after {
  content: '';
  display: block;
  width: 10px;
  height: 10px;
  background-color: white;
  border-radius: 50%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  opacity: 0;
  transition: opacity 0.2s;
}

input[type="radio"]:checked + .custom-radio-circle::after {
  opacity: 1;
}
</style>