<template>
    <div class="check-franchisor">
        <h1>Проверить франчайзера</h1>
        <form @submit.prevent="handleSubmit">
            <div class="form-group">
                <label for="franchisorName">Название франшизы:</label>
                <input type="text" id="franchisorName" v-model="franchisorName"
                    placeholder="Введите название франшизы" />
            </div>
            <button type="submit" :disabled="loading">Проверить</button>
        </form>

        <!-- Анимация ожидания -->
        <div v-if="loading" class="loading">
            <p>Загрузка...</p>
        </div>

        <!-- Результаты -->
        <div v-if="franchisorDetails && !loading" class="details">
            <h2>Детали франчайзера</h2>
            <p><strong>Название:</strong> {{ franchisorDetails.name }}</p>
            <p><strong>Локация:</strong> {{ franchisorDetails.location }}</p>
            <p><strong>Статус:</strong> {{ franchisorDetails.status }}</p>
        </div>

        <!-- Ошибка -->
        <div v-if="error && !loading" class="error">
            <p>{{ error }}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: "CheckFranchisor",
    data() {
        return {
            franchisorName: "", // Название франшизы
            franchisorDetails: null, // Детали франшизы
            loading: false, // Состояние загрузки
            error: null, // Сообщение об ошибке
        };
    },
    methods: {
        async handleSubmit() {
            if (!this.franchisorName.trim()) {
                this.error = "Введите название франшизы.";
                return;
            }

            this.loading = true;
            this.error = null;
            this.franchisorDetails = null;

            try {
                // Отправка запроса на бэкенд
                const response = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/check-franchisor`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ name: this.franchisorName }),
                });

                if (!response.ok) {
                    throw new Error("Ошибка при проверке франчайзера.");
                }

                const data = await response.json();

                if (data.success) {
                    this.franchisorDetails = data.data;
                } else {
                    throw new Error(data.error || "Не удалось получить данные о франчайзере.");
                }
            } catch (error) {
                this.error = error.message;
            } finally {
                this.loading = false;
            }
        },
    },
};
</script>

<style scoped>
.check-franchisor {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;
}

.form-group {
    margin-bottom: 15px;
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
}

button {
    padding: 10px 15px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:disabled {
    background-color: #ccc;
    cursor: not-allowed;
}

button:hover:enabled {
    background-color: #0056b3;
}

.loading {
    margin-top: 20px;
    text-align: center;
    font-size: 16px;
    color: #555;
}

.details {
    margin-top: 20px;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    background-color: #fff;
}

.error {
    margin-top: 20px;
    color: red;
    font-weight: bold;
}
</style>