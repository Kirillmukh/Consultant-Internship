<!-- filepath: c:\Users\dnsco\code\consultant_internship\Consultant-Internship\FrontendConsultantInternship\smart-franchize-website\src\components\HomePage.vue -->
<template>
  <div class="home-page">
    <h1>О нас</h1>
    <p class="about">
      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce non eleifend orci. Vestibulum
nisl odio, aliquam nec suscipit sed, imperdiet sed metus. Ut por ta pretium lectus accumsan
vulputate. Aliquam lectus risus, auctor eu aliquam eget, lacinia a velit. Quisque pulvinar
placerat ex vel eleifend. Morbi sodales suscipit dapibus. Mauris tellus eros, ullamcorper
venenatis imperdiet non, interdum non massa. Morbi rutrum ligula ut volutpat rhoncus.
Aenean at maximus ipsum. Fusce suscipit por ta velit, ut pulvinar sapien maximus ut. Integer
sit amet mi ultrices, posuere sapien id, bibendum ante.
    </p>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  methods: {
    navigateTo(page) {
      this.$router.push({ name: page });
    },
  },
};
</script>

<style scoped>
.home-page {
  text-align: center;
  margin: 0px;
}

h1{
  font-family: 'Tektur';
  font-size: 40px;
  font-weight: 400;
}

.about{
  margin: 40px;
  font-size:22px;
  border: 6px solid #AEADF0;
  border-radius: 20px; 
  padding: 50px 30px;
}

.buttons {
  margin-top: 0px;
}

button {
  margin: 10px;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}
</style>