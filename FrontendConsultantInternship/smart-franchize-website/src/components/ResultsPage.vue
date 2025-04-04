<template>
  <div class="results-page">
    <h1>Ваши результаты</h1>
    <div v-if="loading">Загрузка результатов...</div>
    <div v-else-if="error">{{ error }}</div>
    <div v-else>
      <div class="rating">
        <p><strong>Низкий риск:</strong> {{ lowCount }}</p>
        <p><strong>Средний риск:</strong> {{ moderateCount }}</p>
        <p><strong>Высокий риск:</strong> {{ highCount }}</p>
      </div>
      <button @click="toggleDetails">
        {{ showDetails ? "Скрыть подробные результаты" : "Показать подробные результаты" }}
      </button>
      <ul v-if="showDetails">
        <li v-for="(item, index) in results" :key="index" class="result-item">
          <p><strong>Вопрос {{ item.id }}:</strong> {{ item.question }}</p>
          <p><strong>Ваш ответ:</strong> {{ item.answer }}</p>
          <p><strong>УРОВЕНЬ РИСКА:</strong> {{ item.risk_level }}</p>
          <p v-if="item.explanation"><strong>ПОЧЕМУ:</strong> <span v-html="item.explanation"></span></p>
          <p v-if="item.legal_basis"><strong>ПРАВОВОЕ ОБОСНОВАНИЕ:</strong> <span v-html="item.legal_basis"></span></p>
          <p v-if="item.note"><strong>ОБРАТИТЕ ВНИМАНИЕ!</strong> <span v-html="item.note"></span></p>
        </li>
      </ul>
    </div>
    <button v-if="showDetails" @click="goBack">Вернуться к вопросам</button>
    <button v-if="showDetails" @click="goToNextPage">Перейти к недоговорным рискам</button>
  </div>
</template>

<script>
export default {
  name: "ResultsPage",
  data() {
    return {
      results: [], // Результаты с сервера
      rating: null, // Общий рейтинг
      lowCount: 0, // Количество пунктов с низким риском
      moderateCount: 0, // Количество пунктов с средним риском
      highCount: 0, // Количество пунктов с высоким риском
      showDetails: false, // Флаг для отображения подробных результатов
      loading: true, // Состояние загрузки
      error: null, // Ошибка, если запрос не удался
    };
  },
  methods: {
    async fetchResults() {
      try {
        // Получение ответов пользователя из localStorage
        const answers = JSON.parse(localStorage.getItem("riskAssessmentAnswers"));

        if (!answers) {
          throw new Error("Ответы пользователя не найдены.");
        }

        // Отправка данных на сервер
        const response = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/submit-answers`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(answers),
        });

        if (!response.ok) {
          throw new Error("Ошибка при получении результатов.");
        }

        // Получение результатов
        const data = await response.json();
        this.results = data.data.map((item) => ({
          title: item.title || "Без названия", // Заголовок (если есть)
          question: item.question,
          answer: item.answer,
          risk_level: item.risk_level,
          explanation: item.explanation,
          note: item.note || null, // Только если есть
        }));
        this.lowCount = data.low_count;
        this.moderateCount = data.moderate_count;
        this.highCount = data.high_count;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },
    toggleDetails() {
      this.showDetails = !this.showDetails;
    },
    goBack() {
      this.$router.push({ name: "risk-assessment" });
    },
    goToNextPage() {
      this.$router.push({ name: "non-contractual-risks" });
    },
  },
  mounted() {
    this.fetchResults();
  },
};
</script>

<style scoped>
.results-page {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 8px;
  background-color: #EEE2D4;
  border: 1px solid #ccc;
}

h1{
  font-family: 'Tektur';
  font-size: 40px;
  font-weight: 400;
}

.rating {
  text-align: center;
  margin-bottom: 20px;
}

.result-item {
  margin-bottom: 20px;
  padding: 15px;
  border-radius: 5px;
}

h3 {
  margin-bottom: 10px;
  font-size: 18px;
  color: #333;
}

p {
  margin: 5px 0;
}

button {
  margin: 20px auto;
  display: block;
  padding: 10px 15px;
  background-color: #836645;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #91582F;
}
</style>