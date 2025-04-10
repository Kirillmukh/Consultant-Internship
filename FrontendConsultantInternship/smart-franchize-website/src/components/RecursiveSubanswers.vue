<template>
  <div class="subanswers">
    <div v-for="subanswer in subanswers" :key="subanswer.id" class="option">
      <label>
        <input
          type="radio"
          :name="'subanswer-' + parentId"
          :value="subanswer.id"
          v-model="localAnswers[parentId]"
          @change="handleAnswerChange(subanswer)"
        />
        <span v-html="subanswer.text"></span>
      </label>

      <!-- Рекурсивный вызов для вложенных subanswers -->
      <div v-if="subanswer.subAnswers && localAnswers[parentId] === subanswer.id" class="nested-subanswers">
        <RecursiveSubanswers
          :subanswers="subanswer.subAnswers"
          :parent-id="subanswer.id"
          @update-subanswers="updateParentAnswers"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RecursiveSubanswers",
  props: {
    subanswers: Array,
    parentId: String,
  },
  data() {
    return {
      localAnswers: {}, // Локальные ответы для текущего уровня
    };
  },
  methods: {
    handleAnswerChange(subanswer) {
      // Очищаем вложенные ответы, если выбран другой вариант
      if (!subanswer.subAnswers) {
        this.localAnswers = {};
      }
      this.updateParentAnswers();
    },
    updateParentAnswers() {
      // Передаём обновлённые ответы в родительский компонент
      this.$emit("update-subanswers", this.parentId, this.localAnswers);
    },
  },
};
</script>

<style scoped>
.option {
  margin-bottom: 10px;
}

.nested-subanswers {
  margin-left: 20px;
}
</style>