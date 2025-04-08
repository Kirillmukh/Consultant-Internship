<!-- filepath: c:\Users\dnsco\code\consultant_internship\Consultant-Internship\FrontendConsultantInternship\smart-franchize-website\src\components\AboutFranchising.vue -->
<template>
  <div>
    <h1>Q/A<br>о франчайзинге</h1>
    <div class="container">
      <div v-for="(section, sectionIndex) in sections" :key="sectionIndex" class="section">
        <h2>{{ section.title }}</h2>
        <div class="dropdown">
          <div v-for="(item, index) in section.items" :key="item.id" class="dropdown">
            <button class="dropdown-button" @click="toggleDropdown(sectionIndex, index)">
              <span class="icon" :class="{ 'rotated': item.isOpen }">></span>
              {{ item.title }}
            </button>
            <transition name="slide">
              <div v-show="item.isOpen" class="dropdown-content">
                <div v-html="item.content"></div>
              </div>
            </transition>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { faqData } from "@/data/FAQ";

export default {
  name: "AboutFranchising",
  data() {
    return {
      sections: faqData
    };
  },
  methods: {
    toggleDropdown(sectionIndex, itemIndex) {
      this.sections[sectionIndex].items[itemIndex].isOpen = !this.sections[sectionIndex].items[itemIndex].isOpen;
    }
  }
};
</script>

<style scoped>
h1 {
  font-family: 'Tektur';
  font-size: 40px;
  font-weight: 500;
}

.container {
  max-width: 1200px;
  width: 100%;
  text-align: start;
}

h2 {
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

p {
  font-size: 22px;
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