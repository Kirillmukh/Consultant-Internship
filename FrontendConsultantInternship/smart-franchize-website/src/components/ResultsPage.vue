<template>
  <div class="results-page">
    <h1>Ваши результаты</h1>
    <div v-if="loading">Загрузка результатов...</div>
    <div v-else-if="error">{{ error }}</div>
    <div v-else>
      <div class="rating">
        <p><strong>Ответов<br> c низким риском:</strong> {{ lowCount }}</p>
        <p><strong>c средним риском:</strong> {{ moderateCount }}</p>
        <p><strong>c высоким риском:</strong> {{ highCount }}</p>
      </div>
      <!-- <button @click="toggleDetails">
        {{ showDetails ? "Скрыть подробные результаты" : "Показать подробные результаты" }}
      </button> -->
      <ul >
        <li v-for="(item, index) in results" :key="index" class="result-item">
          <p><strong>Вопрос:</strong> {{ item.question }}</p>
          <p><strong>Ваш ответ:</strong> {{ item.answer }}</p>
          <p><strong>УРОВЕНЬ РИСКА:</strong> {{ item.risk_level }}</p>
          <p v-if="item.explanation"><strong>ПОЧЕМУ:</strong> <span v-html="item.explanation"></span></p>
          <p v-if="item.legal_basis"><strong>ПРАВОВОЕ ОБОСНОВАНИЕ:</strong> <span v-html="item.legal_basis"></span></p>
          <p v-if="item.note"><strong>ОБРАТИТЕ ВНИМАНИЕ!</strong> <span v-html="item.note"></span></p>
        </li>
      </ul>
    </div>
    <button v-if="showDetails" @click="goBack">Вернуться к вопросам</button>
    <!-- <button v-if="showDetails" @click="goToNextPage">Перейти к недоговорным рискам</button> -->
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
        const subAnswers = JSON.parse(localStorage.getItem("riskAssessmentSubAnswers"));

        if (!answers) {
          throw new Error("Ответы пользователя не найдены.");
        }

        // Объединяем ответы с учётом subAnswers
        const finalAnswers = { ...answers };
        for (const [optionId, subAnswerId] of Object.entries(subAnswers || {})) {
          if (finalAnswers[optionId]) {
            finalAnswers[optionId] = subAnswerId; // Заменяем значение на выбранный subAnswer
          }
        }

        // Отправка данных на сервер
        const response = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/v1/submit-answers`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(finalAnswers),
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
        this.lowCount = data.risks_count.Низкий;
        this.moderateCount = data.risks_count.Средний;
        this.highCount = data.risks_count.Высокий;
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
  text-align: left;
  background-color: white;
  border-radius: 20px;
}

h3 {
  margin-bottom: 10px;
  font-size: 18px;
  color: #333;
}

p {
  margin: 5px 0;
}

ul{
  list-style-type: none;
}

button {
  margin: 20px;
  height: 35px;
  width: 400px;
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
</style>