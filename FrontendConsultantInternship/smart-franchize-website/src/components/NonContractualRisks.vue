<template>
  <div>
    <h1>Памятки для франчайзи</h1>
    <div class="container">
    <!-- <ul> -->
      <div class="navigations_item">
        <router-link to="/memo/marketing.html" class="navigations_text">Реклама и маркетинг</router-link>
        <!-- <a href="https://example.com/article1" target="_blank" rel="noopener noreferrer">
          Статья 1: Риски в ценообразовании
        </a> -->
      </div>
      <div class="navigations_item">
        <router-link to="/memo/assurances.html" class="navigations_text">Памятка о заверениях правообладателя (франчайзера)</router-link>
        <!-- <a href="https://example.com/article2" target="_blank" rel="noopener noreferrer">
          Статья 2: Досудебное урегулирование споров
        </a> -->
      </div>
      <div class="navigations_item">
        <router-link to="/memo/check.html" class="navigations_text">Памятка о проверке правообладателя (франчайзера)</router-link>
        <!-- <a href="https://example.com/article3" target="_blank" rel="noopener noreferrer">
          Статья 3: Продление договора франчайзинга
        </a> -->
      </div>
      <div class="navigations_item">
        <router-link to="/memo/essential_conditions.html" class="navigations_text">Памятка о существенных условиях</router-link>
        <!-- <a href="https://example.com/article4" target="_blank" rel="noopener noreferrer">
          Статья 4: Регистрация договора
        </a> -->
      </div>
      <div class="navigations_item">
        <router-link to="/memo/opening.html" class="navigations_text">Памятка о процедуре открытия франшизы кофейни</router-link>
        <!-- <a href="https://example.com/article5" target="_blank" rel="noopener noreferrer">
          Статья 5: Консультации и поддержка
        </a> -->
      </div>
      <div class="navigations_item">
        <router-link to="/memo/economic_risks.html" class="navigations_text">Экономические риски</router-link>
        <!-- <a href="https://example.com/article5" target="_blank" rel="noopener noreferrer">
          Статья 5: Консультации и поддержка
        </a> -->
      </div>
    <!-- </ul> -->
    </div>
  </div>
</template>

<script>
export default {
  name: "NonContractualRisks",
};
</script>

<style scoped>
/* .non-contractual-risks {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  text-align: left;
} */

h1 {
  font-family: 'Tektur';
  font-size: 40px;
  font-weight: 500;
}

.container {
  max-width: 1200px;
  width: 100%;
  text-align: start;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 10px;
}

.navigations_item {
  position: relative;
  font-family: 'Tektur';
  font-size: 30px;
  height: 60px;
  align-content: center;

  background-color: #EEE2D4;
  font-weight: 500;
  padding-left: 25px;
  border-radius: 20px;
  border: 1px solid #ccc;

  color: #626262;
  text-decoration: none;

  transition: color 0.3s ease;
  cursor: pointer;
  font-size: 20px;
  margin-bottom: 20px;
}

.navigations_item:hover{
  text-decoration: none;
  color: #000000;
}

.navigations_text{
  color: #626262;
}

.navigations_text:hover{
  text-decoration: none;
  color: #000000;
}

a {
  color: #007bff;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>