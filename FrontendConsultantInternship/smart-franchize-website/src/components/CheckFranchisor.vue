<template>
    <div class="check-franchisor">
        <h1>Check Franchisor</h1>
        <form @submit.prevent="handleSubmit">
            <div class="form-group">
                <label for="franchisorName">Franchisor Name:</label>
                <input
                    type="text"
                    id="franchisorName"
                    v-model="franchisorName"
                    placeholder="Enter franchisor name"
                />
            </div>
            <button type="submit">Check</button>
        </form>
        <div v-if="franchisorDetails" class="details">
            <h2>Franchisor Details</h2>
            <p><strong>Name:</strong> {{ franchisorDetails.name }}</p>
            <p><strong>Location:</strong> {{ franchisorDetails.location }}</p>
            <p><strong>Status:</strong> {{ franchisorDetails.status }}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: "CheckFranchisor",
    data() {
        return {
            franchisorName: "",
            franchisorDetails: null,
        };
    },
    methods: {
        handleSubmit() {
            // Simulate fetching franchisor details
            this.franchisorDetails = {
                name: this.franchisorName,
                location: "New York, USA",
                status: "Active",
            };
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

button:hover {
    background-color: #0056b3;
}

.details {
    margin-top: 20px;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    background-color: #fff;
}
</style>