<template>
  <div id="app">
    <header>
      <div class="container">
        <h1>
          <router-link to="/" class="home-link">СмартФранчайз</router-link>
        </h1>
        <p>Ваш юридический помощник в мире франчайзинга</p>
      </div>
    </header>
    <main>
      <router-view />
    </main>
    <footer>
      <p>&copy; 2025 СмартФранчайз. Сделано командой 4</p>
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
  background: #f5f5f5;
  color: #333;
  padding: 30px 20px;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

header .container {
  max-width: 1200px;
  margin: 0 auto;
}

header h1 {
  font-size: 32px;
  margin: 0;
  font-weight: bold;
}

header h1 .home-link {
  color: #333;
  text-decoration: none;
  transition: color 0.3s ease;
}

header h1 .home-link:hover {
  color: #4caf50;
}

header p {
  font-size: 16px;
  margin-top: 10px;
  opacity: 0.8;
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
    font-size: 24px;
  }

  header p {
    font-size: 14px;
  }

  main {
    padding: 10px;
  }
}
</style>
