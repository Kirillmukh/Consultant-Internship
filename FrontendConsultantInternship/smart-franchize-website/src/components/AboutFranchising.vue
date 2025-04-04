<!-- filepath: c:\Users\dnsco\code\consultant_internship\Consultant-Internship\FrontendConsultantInternship\smart-franchize-website\src\components\AboutFranchising.vue -->
<template>
  <div>
    <h1>Q/A<br>о франчайзинге</h1>
  </div>
  <div class = "container">
    <h2>Общие вопросы о франчайзинге кофеен</h2>
    <div class="dropdown">
      <div v-for="(item, index) in items" :key="item.id" class="dropdown">
      <button class="dropdown-button" @click="toggleDropdown(index)">
        <span class="icon" :class="{ 'rotated': item.isOpen }">></span>
        {{ item.title }}
      </button>
        <transition name="slide">
          <div v-show="item.isOpen" class="dropdown-content">
          {{ item.content }}
        </div>
        </transition>
      </div>
    </div>
    <h2>Следующий раздел вопросов</h2>
  </div>
</template>

<script>
export default {
  name: 'AboutFranchising',
  data() {
    return {
      items: [
        {
          id: 1,
          title: "Как открыть франшизу кофейни?",
          content: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce non eleifend orci. Vestibulum nisl odio, aliquam nec suscipit sed, imperdiet sed metus. Ut porta pretium lectus accumsan vulputate. Aliquam lectus risus, auctor eu aliquam eget, lacinia a velit. Quisque pulvinar placerat ex vel eleifend. Morbi sodales suscipit dapibus. Mauris tellus eros, ullamcorper venenatis imperdiet n",
          isOpen: false
        },
        {
          id: 2,
          title: "Вопрос 2",
          content: "Ответ на вопрос 2...",
          isOpen: false
        },
        {
          id: 3,
          title: "Вопрос 3",
          content: "Ответ на вопрос 3...",
          isOpen: false
        }
      ]
    }
  },
  methods: {
    toggleDropdown(index) {
      // Инвертируем состояние только выбранного элемента
      this.items[index].isOpen = !this.items[index].isOpen
    }
  }
};
</script>

<style scoped>
h1{
  font-family: 'Tektur';
  font-size: 40px;
  font-weight: 500;
}

.container{
  max-width: 1200px;
  width: 100%;
  text-align: start;
}

h2{
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
}

.dropdown {
  width: 100%;
  margin: 20px;
}

.dropdown-button {
  border: none;
  display: flex;
  align-items: center;
  font-size: 26px;
  font-weight: 400;
  gap: 8px;
  padding: 10px 15px;
  background: white;
  cursor: pointer;
  transition: all 0.3s;
}

.icon {
  transition: transform 0.3s;
  font-size: 26px;
  color: #836645;
}

.rotated {
  transform: rotate(90deg);
}

.dropdown-content {
  text-align: left;
  margin: 10px;
  padding: 20px 30px;
  border: 6px solid #91582F;
  border-radius: 20px;
}

p{
  font-size:22px;
  border-radius: 20px; 
  padding: 50px 30px;
}

/* Анимация выпадающего блока */
.slide-enter-active,
.slide-leave-active {
  transition: all 0.3s ease;
}

.slide-enter-from,
.slide-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}
</style>