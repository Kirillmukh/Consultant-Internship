<template>
  <div id="app">
    <header>
      <div class="logo">
        <router-link to="/" class="home-link">Смарт Франчайз</router-link>
      </div>
      <div class="navigations">
        <router-link to="/" class="navigations_item">О нас<div class="underline"></div></router-link>
        <router-link to='/about-franchising' class="navigations_item">Q/A<br> о франчайзинге<div class="underline">
          </div></router-link>
        <router-link to="/check-franchisor" class="navigations_item">Проверить франчайзера<div class="underline"></div>
          </router-link>
        <div class="navigations_item" @click="navigateToRiskAssessment">Оценить риски<div class="underline"></div>
        </div>
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
  data() {
    return {
      heartbeatInterval: null, // Таймер для heartbeat
    };
  },
  methods: {
    sendHeartbeat() {
      // Собираем данные о текущем состоянии
      const data = {
        timestamp: new Date().toISOString(),
        currentPage: this.$route.name, // Текущая страница
        userAgent: navigator.userAgent, // Информация о браузере
      };

      // Отправляем данные на сервер
      fetch(`${process.env.VUE_APP_BACKEND_URL}/api/heartbeat`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error('Ошибка при отправке heartbeat');
          }
          console.log('Heartbeat отправлен:', data);
        })
        .catch((error) => {
          console.error('Ошибка heartbeat:', error);
        });
    },
    navigateToRiskAssessment() {
      // Проверяем наличие прогресса в localStorage
      const progress = localStorage.getItem('riskAssessmentAnswers');
      if (progress) {
        // Если прогресс есть, переходим на страницу анкеты
        this.$router.push({ name: 'risk-assessment' });
      } else {
        // Если прогресса нет, переходим на страницу критических рисков
        this.$router.push({ name: 'critical-risks' });
      }
    },
  },
  mounted() {
    // Запускаем heartbeat при загрузке приложения
    this.heartbeatInterval = setInterval(this.sendHeartbeat, 30000); // Отправка каждые 30 секунд
  },
  beforeUnmount() {
    // Очищаем таймер при уничтожении компонента
    if (this.heartbeatInterval) {
      clearInterval(this.heartbeatInterval);
    }
  },
};
</script>

<style>
/* Общие стили */
#app {
  font-family: 'Inter', Arial, sans-serif;
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
  max-width: 1200px;
  width: 100%;
  margin: 0 auto;
  padding: 0px 20px;
  text-align: center;
  font-family: 'Tektur';

  font-weight: 500;
}

.logo {

  position: relative;

  width: 70px;
  /* Ширина блока */
  height: 70px;
  /* Высота блока */
  margin: 0px;
  top: 30px;
  left: 0px;
  background: #91582F;
  /* Цвет фона */
  border: 1px solid #000000;

  font-size: clamp(12px, 0.5vw, 18px);

}

header .home-link {
  position: absolute;
  /* Абсолютное позиционирование */
  bottom: 5px;
  /* Отступ от нижнего края */
  left: 5px;
  right: 1px;
  color: #000000;
  text-decoration: none;
  text-align: left;
  line-height: 1;
  transition: color 0.3s ease;

  z-index: 1;
  /* Поднимаем над фоном */

  max-width: 100%;
}

header .home-link:hover {
  color: #ffffff;
}

header .navigations {
  position: relative;
  display: flex;
  justify-content: flex-end;
  align-items: right;
  bottom: 50px;
}

header .navigations_item {
  height: 80px;
  width: 170px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;

  position: relative;
  margin: 10px;
  color: #626262;
  text-decoration: none;
  font-size: 18px;

  transition: color 0.3s ease;
  cursor: pointer;
  /* Добавлено для изменения курсора */
}

header .navigations_item:hover {
  color: #000000;
}

.router-link-active {
  color: #000000;
  ;
  /* Цвет текста активной ссылки */
}

.underline {
  position: absolute;
  width: 120px;
  bottom: 4px;
  left: 0;
  width: 100%;
  height: 5px;
  border-radius: 2.5px;
  ;
  background: #D9C4AB;
  /* Серый цвет по умолчанию */
  transition: background 0.3s;
}

.router-link-active .underline {
  background: #91582F;
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


hint {
  color: #666666;
  text-decoration: underline;
  cursor: pointer;
}

/* Стили для футера */
footer {
  padding: 20px 10px;
  text-align: center;
  /* Выровнять текст по центру */
  color: #333;
}
</style>
