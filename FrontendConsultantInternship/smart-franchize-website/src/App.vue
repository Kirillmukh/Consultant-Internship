<template>
  <div id="app">
    <header>
      <div class="logo_div">
        <router-link to="/" class="home-link">
          <img src="@/assets/SF.png" alt="Логотип" class=logo>
        </router-link>
      </div>
      <div class="navigations">
        <!-- <router-link to="/" class="navigations_item">Главная<div class="underline"></div></router-link> -->
        <div class="navigations_item" @click="navigateToRiskAssessment" :class="{ 'active': isActive }">Оценить риски<div class="underline"></div></div>
        <router-link to='/about-franchising' class="navigations_item">Q/A<br> о франчайзинге<div class="underline"></div></router-link>
        <router-link to="/memos" class="navigations_item">Памятки для франчайзи<div class="underline"></div></router-link>
      </div>
    </header>
    <main>
      <router-view />
    </main>
    <footer><p>
      Информация, содержащаяся на Сайте, включая авторские материалы (комментарии, статьи, ответы на вопросы и т.д.), имеет справочный характер.<br>
      Актуально на 17.04.2025<br>
      <!--Компания «СмартФранчайз»‎ не несет ответственности за правильность информации, изложенной в авторских материалах.<br> -->
      &copy; 2025 СмартФранчайз
    </p>
    </footer>
  </div>
</template>

<script>
import { sendHeartbeat, trackPageVisit } from "@/utils/tracking";

export default {
  name: "App",
  data() {
    return {
      heartbeatInterval: null, // Таймер для heartbeat
    };
  },
  computed: {
    isActive() {
      // Проверяем текущий путь относительно нужных страниц
      const activePages = [
        '/risk-assessment',
        '/critical-risks',
        '/results'
      ]
      return activePages.includes(this.$route.path)
    }
  },
  methods: {
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
    // Запускаем heartbeat каждые 30 секунд
    this.heartbeatInterval = setInterval(() => {
      sendHeartbeat(this.$route.name);
    }, 30000);

    // Отслеживаем переходы между страницами
    this.$router.afterEach((to, from) => {
      trackPageVisit(to, from);
    });
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
  margin: 0 auto;
  padding: 0;
}

/* Стили для заголовка */
header {
  max-width: 1200px;
  width: 100%;
  margin: 1px auto;
  padding: 0px 20px;
  text-align: center;
  font-family: 'Tektur';

  font-weight: 500;
}

.logo_div {
  position: relative;
  cursor: pointer;
  width: 450px; /* Ширина блока */
  height: 87px; /* Высота блока */
  margin: 0px;
  margin-left: 1px;
  top: 27px;
  left: 0;
}

.logo  {

  position: relative;
  cursor: pointer;
  width: 450px; /* Ширина блока */
  height: 87px; /* Высота блока */
  margin: 0px;
  top: 27px;
  left: 0;
}

header .home-link {
  position: absolute;        /* Абсолютное позиционирование */
  right: 0px;
  z-index: 1;        /* Поднимаем над фоном */
  max-width: 100%;
}

header .navigations{
  position: relative; 
  display: flex;
  justify-content: flex-end;
  align-items: right;
  bottom: 50px;
  right: -20px;
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

.navigations_item.active .underline {
  background: #91582F; /* Цвет подчеркивания для активной страницы */
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
  font-size: 14px;;
}
</style>
