<template>
  <div class="home-page">
    <p class="about">
      Приветствуем Вас на сайте проекта "СмартФранчайз"!<br><br>
Наш проект позволяет потенциальному пользователю (франчайзи):<br>
- рассчитать уровень риска договора коммерческой концессии (франчайзинга)<br>
- узнать ответы на распространенные вопросы о франшизе кофейни<br>
- узнать больше о договоре коммерческой концессии (франчайзинга)
    </p>
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
  font-family: 'Tektur';
  text-align: center;
  margin: 0px;
}

h1 {
  font-family: 'Tektur';
  font-size: 40px;
  font-weight: 400;
}

.about {
  letter-spacing: 1px;
  font-family: 'Tektur';
  margin: 40px;
  font-size: 22px;
  border: 6px solid #91582F;
  border-radius: 20px;
  padding: 50px 30px;
}

.buttons {
  margin-top: 0px;
}

button {
  margin: 10px;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}
</style>