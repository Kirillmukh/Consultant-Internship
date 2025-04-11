// utils/session.js
export function getSessionId() {
    let sessionId = localStorage.getItem("sessionId");
    if (!sessionId) {
      sessionId = crypto.randomUUID(); // Генерация уникального идентификатора
      localStorage.setItem("sessionId", sessionId);
    }
    return sessionId;
  }