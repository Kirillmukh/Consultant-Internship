<template>
  <div class="subanswers">
    <div v-for="subanswer in subanswers" :key="subanswer.id" class="option">
      <label>
        <input
          type="radio"
          :name="'subanswer-' + parentId + '-' + subanswer.id"
          :value="subanswer.id"
          v-model="localAnswers[subanswer.id]"
          @change="updateParentAnswers"
        />
        <span v-html="subanswer.text"></span>
      </label>

      <!-- Рекурсивный вызов для вложенных subanswers -->
      <div v-if="subanswer.subAnswers && localAnswers[subanswer.id]" class="nested-subanswers">
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
      localAnswers: {},
    };
  },
  methods: {
    updateParentAnswers() {
      this.$emit("update-subanswers", this.parentId, this.localAnswers);
    },
  },
};
</script>

<style scoped>
/* Добавьте стили для оформления */
</style>