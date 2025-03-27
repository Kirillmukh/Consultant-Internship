<template>
  <div id="app">
    <header>
      <div class="container">
        <h1>СмартФранчайз</h1>
        <p>Ваш юридический помощник в мире франчайзинга</p>
      </div>
    </header>
    <main>
      <router-view />
    </main>
    <footer>
      <p>&copy; 2025 СмартФранчайз. Все права защищены.</p>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
/* Общие стили */
#app {
  font-family: 'Roboto', Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #333;
  line-height: 1.6;
  margin: 0;
  padding: 0;
}

/* Стили для заголовка */
header {
  background: linear-gradient(135deg, #4caf50, #81c784);
  color: white;
  padding: 40px 20px;
  text-align: center;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

header .container {
  max-width: 1200px;
  margin: 0 auto;
}

header h1 {
  font-size: 36px;
  margin: 0;
  font-weight: bold;
}

header p {
  font-size: 18px;
  margin-top: 10px;
  opacity: 0.9;
}

/* Стили для основного контента */
main {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

/* Стили для футера */
footer {
  background: #f4f4f4;
  color: #666;
  padding: 20px 10px;
  border-top: 1px solid #ddd;
  font-size: 14px;
  text-align: center;
}

footer p {
  margin: 0;
}

/* Адаптивность */
@media (max-width: 768px) {
  header h1 {
    font-size: 28px;
  }

  header p {
    font-size: 16px;
  }

  main {
    padding: 10px;
  }
}
</style>
