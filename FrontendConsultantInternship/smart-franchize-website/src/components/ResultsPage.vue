<template>
    <div class="results-page">
      <h1>Ваши результаты</h1>
      <div v-if="loading">Загрузка результатов...</div>
      <div v-else-if="error">{{ error }}</div>
      <div v-else>
        <ul>
          <li v-for="(result, index) in results" :key="index">{{ result }}</li>
        </ul>
      </div>
      <button @click="goBack">Вернуться к вопросам</button>
    </div>
  </template>
  
  <script>
  export default {
    name: "ResultsPage",
    data() {
      return {
        results: [], // Результаты с сервера
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
          this.results = data.data;
        } catch (error) {
          this.error = error.message;
        } finally {
          this.loading = false;
        }
      },
      goBack() {
        this.$router.push({ name: "risk-assessment" });
      },
    },
    mounted() {
      this.fetchResults();
    },
  };
  </script>
  
  <style scoped>
  .results-page {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    text-align: center;
  }
  
  h1{
  font-family: 'Tektur';
  font-size: 40px;
  font-weight: 400;
  } 

  button {
    margin-top: 20px;
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
  </style>