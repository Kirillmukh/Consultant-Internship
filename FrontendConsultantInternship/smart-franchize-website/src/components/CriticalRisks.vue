<template>
  <div class="critical-risks">
    <h1>Критические риски</h1>
    <ul>
      <li>Риск 1: Невозможность самостоятельного ценообразования</li>
      <li>Риск 2: Отсутствие досудебного урегулирования споров</li>
      <li>Риск 3: Потеря бизнеса после истечения срока договора</li>
      <li>Риск 4: Регистрация договора за счет франчайзи</li>
      <li>Риск 5: Отсутствие консультаций и поддержки</li>
    </ul>
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
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  text-align: left;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
}

ul {
  list-style-type: none;
  padding: 0;
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