<template>
  <div id="home">
    <img src="@/assets/cutezi.jpg" alt="Cute Laozi" />
    <div id="user-input">
      <div id="title">
        <h1>The Old Master Said</h1>
      </div>
      <div id="box">
        <h2>Please insert an English quote form the Daodejing here:</h2>
        <textarea id="input" name="input" rows="20" cols="100" />
        <div></div>
        <button type="button">Submit</button>
        <div></div>
        <input type="text" id="chapters" name="chapters" />
        <button type="button">Check Chapter</button>
        <h3>
          You may also choose a chapter, here, and compare all the English
          versions of that chapter found in our database.
        </h3>
      </div>
    </div>
  </div>
</template>

<script>
// import titleText from "@/components/TitleText.vue";

export default {
  components: {},
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
