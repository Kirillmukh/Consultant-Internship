<template>
  <div class="critical-risks">
    <p class="warning-paragraph">
      <i>Перед началом прохождения опроса советуем Вам удостовериться, что в договоре прописаны все существенные
        условия.</i><br>
      <i class="warning-text">!!! Отсутствие в договоре существенных условий влечет признание договора
        незаключенным.</i>
    </p>
    <p>
      <b>Существенными условиями договора коммерческой концессии (франчайзинга) признаются (<a
          href='https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102434'
          target='_blank'>Статья 1027 ГК РФ</a>):</b>
    </p>
    <ul>
      <li>Предмет договора.</li>
      <li>Определение комплекса принадлежащих правообладателю исключительных прав, включающий право на товарный знак,
        знак обслуживания, а также права на другие предусмотренные договором объекты исключительных прав, в частности на
        коммерческое обозначение, секрет производства (ноу-хау), которые предоставляются Пользователю.</li>
      <li>Определение суммы вознаграждения, уплачиваемого Правообладателю.</li>
      <li>Наличие условия о виде предпринимательской деятельности, предполагаемой к осуществлению Пользователем в рамках
        договора.</li>
    </ul>
    <p>
      Ознакомиться с существенными условиями подробнее можно в <a href="memo/essential_conditions.html"
        target="_blank">Памятке о существенных условиях договора коммерческой концессии</a>.
    </p>
    <button @click="goToQuestionnaire">Все эти условия прописаны</button>
    <!-- <p class="warning-paragraph">
      <i>Также рекомендуем Вам воспользоваться нашим сервисом “<a href="/check-franchisor" target="_blank">Проверить
          Франчайзера</a>”, чтобы проверить Вашего контрагента по договору на предмет возможной
        недобросовестности.</i><br>
      Зачем проверять франчайзера? Ответ Вы можете найти в памятке “<a href="memo/check.html" target="_blank">О проверке
        Правообладателя</a>”
    </p> -->
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
  background-color: #836645;
  color: white;
  border: none;
  border-radius: 10px;
  font-family: 'Tektur';
  font-size: 16px;
  cursor: pointer;
}

button:hover {
  background-color: #91582F;
}

.warning-paragraph {
  text-align: center;
}

.warning-text {
  color: red;
  /* Красный цвет текста */
}
</style>