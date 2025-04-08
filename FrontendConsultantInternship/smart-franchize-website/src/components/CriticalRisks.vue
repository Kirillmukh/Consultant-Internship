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
          href='https://www.consultant.ru/document/cons_doc_LAW_9027/49c2afdf04c1ba13e815aa8b44287cd4b6cac9f5/'
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
    <button @click="goToQuestionnaire">Все эти условия прописаны</button>
    <p class="warning-paragraph">
      <i>Также рекомендуем Вам воспользоваться нашим сервисом <b><a href="/check-franchisor">“Проверить
            Франчайзера”</a></b>, чтобы проверить Вашего контрагента по договору на предмет возможной
        недобросовестности.</i>
    </p>
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

.warning-paragraph {
  text-align: center;
}

.warning-text {
  color: red;
  /* Красный цвет текста */
}
</style>