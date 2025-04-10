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
        <span v-if="subanswer.hint" class="tooltip" :title="subanswer.hint">?</span>
      </label>

      <!-- Рекурсивный вызов для вложенных subanswers -->
      <div v-if="subanswer.subanswers && localAnswers[subanswer.id]" class="nested-subanswers">
        <RecursiveSubanswers
          :subanswers="subanswer.subanswers"
          :parent-id="subanswer.id"
          :modelValue="localAnswers[subanswer.id]"
          @update:modelValue="value => localAnswers[subanswer.id] = value"
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
    parentAnswer: String,
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