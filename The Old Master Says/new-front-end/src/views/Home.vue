<template>
  <div id="home">
    <img src="@/assets/cutezi.jpg" alt="Cute Laozi" />
    <div id="user-input">
      <div id="title">
        <h1>The Old Master Said</h1>
      </div>
      <search-quote></search-quote>
    </div>
  </div>
</template>

<script>
import searchQuote from "@/components/SearchQuote.vue";

export default {
  components: {
    searchQuote,
  },
};
</script>


<style>
#home {
  display: flex;
}

#home img {
  width: auto;
  height: 100vh;
}

#title {
}

#title h1 {
  font-size: 150px;
  border: 0;
  margin: 0;
}

#user-input {
  display: flex;
  flex-direction: column;
}

#box {
}
</style>
