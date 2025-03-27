<!-- filepath: c:\Users\dnsco\code\consultant_internship\Consultant-Internship\FrontendConsultantInternship\smart-franchize-website\src\components\HomePage.vue -->
<template>
  <div class="home-page">
    <h1>Добро пожаловать на СмартФранчайз</h1>
    <p>
      СмартФранчайз — это юридический инструмент, который помогает потенциальным франчайзи
      найти больше информации о договоре франчайзинга, который они хотят выбрать.
    </p>
    <div class="buttons">
      <button @click="navigateTo('about-franchising')">О франчайзинге</button>
      <button @click="navigateTo('risk-assessment')">Оценить риски</button>
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