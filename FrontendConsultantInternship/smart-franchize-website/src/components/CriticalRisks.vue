<template>
  <div class="critical-risks">
    <h1>Страница про критические риски</h1>
    <p>
      Здесь будет информация о критических рисках, связанных с франчайзингом
    </p>
    <ul>
      <li>Такой-то риск</li>
      <li>Ещё один риск</li>
      <li>Третий риск</li>
    </ul>
    <p>
      Ещё какой-то сопровождающий текст
    </p>
    <button @click="goToQuestionnaire">Перейти к анкете</button>
  </div>
</template>

<script>
export default {
  name: "CriticalRisks",
  methods: {
    goToQuestionnaire() {
      this.$router.push({ name: "risk-assessment" });
    },
  },
};
</script>

<style scoped>
.critical-risks {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  text-align: left;
  border: 1px solid #ccc;
  border-radius: 20px;
  background-color: #EEE2D4;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
}

li {
  margin-bottom: 10px;
}

button {
  display: block;
  margin: 20px auto 0;
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