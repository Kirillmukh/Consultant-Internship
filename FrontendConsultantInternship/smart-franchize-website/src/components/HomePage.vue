<template>
  <div class="home-page">
    <h1>Здесь будет приветствие</h1>
    <p>
      Здесь будет описание
    </p>
    <div class="buttons">
      <button @click="navigateTo('about-franchising')">О франчайзинге</button>
      <button @click="checkProgress()">Оценить риски</button>
      <button @click="navigateTo('check-franchisor')">Проверить франчайзера</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  methods: {
    navigateTo(page) {
      this.$router.push({ name: page });
    },
    checkProgress() {
      const progress = localStorage.getItem('riskAssessmentAnswers');
      if (progress) {
        // Если есть прогресс, перенаправляем на анкету
        this.$router.push({ name: 'risk-assessment' });
      } else {
        // Если прогресса нет, перенаправляем на критические риски
        this.$router.push({ name: 'critical-risks' });
      }
    },
  },
};
</script>

<style scoped>
.home-page {
  text-align: center;
  margin: 20px;
}

.buttons {
  margin-top: 20px;
}

button {
  margin: 10px;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}
</style>