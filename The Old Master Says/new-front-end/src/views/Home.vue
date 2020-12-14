<template>
  <div id="home">
    <title-text />
    <background></background>
  </div>
</template>

<script>
import titleText from "@/components/TitleText.vue";
import Background from "@/components/Background.vue";

export default {
  components: {
    titleText,
    Background,
  },
};
</script>

<style scoped>
</style>
