// utils/tracking.js
import { getSessionId } from "./session";

const API_URL = process.env.VUE_APP_BACKEND_URL;

let maxScrollDepth = 0; // Глобальная переменная для хранения максимальной прокрутки
let currentScrollDepth = 0; // Переменная для хранения текущей прокрутки

// Отслеживание прокрутки
window.addEventListener("scroll", () => {
  const scrollTop = window.scrollY; // Текущая позиция прокрутки
  const windowHeight = window.innerHeight; // Высота окна
  const documentHeight = document.documentElement.scrollHeight; // Высота всего документа

  // Вычисляем процент прокрутки
  currentScrollDepth = Math.min(
    100,
    Math.round((scrollTop + windowHeight) / documentHeight * 100)
  );

  // Сохраняем максимальную достигнутую прокрутку
  maxScrollDepth = Math.max(maxScrollDepth, currentScrollDepth);
});

export function sendHeartbeat(routeName) {
  // Извлекаем текущий вопрос из localStorage
  const currentQuestionIndex = parseInt(localStorage.getItem("riskAssessmentCurrentQuestion"), 10);
  const questions = JSON.parse(localStorage.getItem("riskAssessmentQuestions")) || [];
  const currentQuestionId = questions[currentQuestionIndex]?.id || null;

  const data = {
    sessionId: getSessionId(), // Идентификатор сессии
    timestamp: new Date().toISOString(),
    currentPage: routeName, // Текущая страница
    currentQuestionId, // ID текущего вопроса
    maxScrollDepth, // Максимальная достигнутая прокрутка
    currentScrollDepth, // Текущая прокрутка
    userAgent: navigator.userAgent, // Информация о браузере
  };

  fetch(`${API_URL}/api/v1/heartbeat`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  }).catch((error) => {
    console.error("Ошибка отправки heartbeat:", error);
  });
}

export function trackPageVisit(to, from) {
  const data = {
    sessionId: getSessionId(),
    timestamp: new Date().toISOString(),
    fromPage: from.name || "unknown",
    toPage: to.name || "unknown",
    userAgent: navigator.userAgent,
  };

  fetch(`${API_URL}/api/v1/page-visit`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  }).catch((error) => {
    console.error("Ошибка отправки данных о посещении страницы:", error);
  });
}