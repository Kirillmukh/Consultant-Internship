// utils/session.js
import { v4 as uuidv4 } from "uuid";

export function getSessionId() {
    let sessionId = localStorage.getItem("sessionId");
    if (!sessionId) {
      sessionId = uuidv4(); // Генерация уникального идентификатора
      localStorage.setItem("sessionId", sessionId);
    }
    return sessionId;
  }