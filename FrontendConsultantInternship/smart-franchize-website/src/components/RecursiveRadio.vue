<template>
  <div class="recursive-radio">
    <div v-for="answer in answers" :key="answer.id" class="answer">
      <label>
        <!-- Группируем радиокнопки по переданному groupName -->
        <input 
          type="radio"
          :name="groupName"
          :value="answer.id"
          v-model="selected"
          @change="onRadioChange(answer)" />
        {{ answer.text }}
      </label>
      <!-- Если у варианта есть подварианты, рендерим их рекурсивно.
           Формируем уникальное имя группы для каждого уровня -->
      <div v-if="answer.subAnswers" class="subanswers">
        <RecursiveRadio 
          :answers="answer.subAnswers" 
          :group-name="groupName + '-' + answer.id" 
          @answer-selected="onSubAnswerSelected(answer, $event)" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RecursiveRadio",
  components: {
    // Рекурсивный импорт самого себя
    RecursiveRadio: () => import("./RecursiveRadio.vue"),
  },
  props: {
    answers: {
      type: Array,
      required: true
    },
    groupName: {
      type: String,
      default: "default-group"
    }
  },
  data() {
    return {
      selected: null,
    };
  },
  methods: {
    onRadioChange(answer) {
      // Эмитим событие answer-selected с выбранным ответом
      this.$emit("answer-selected", { id: answer.id, groupName: this.groupName });
    },
    onSubAnswerSelected(parentAnswer, payload) {
      // Если выбран subanswer, можно комбинировать id родительского варианта и subanswer-а
      // Например, если родительский ответ имеет id "A", а subanswer "B", итоговый id: "A-B"
      const combinedId = `${parentAnswer.id}-${payload.id}`;
      this.$emit("answer-selected", { id: combinedId, groupName: this.groupName });
    }
  }
};
</script>

<style scoped>
.recursive-radio {
  margin-left: 1em;
  padding-left: 1em;
  border-left: 1px dotted #aaa;
}

.answer {
  margin: 0.5em 0;
}
</style>
