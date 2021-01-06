<template>
  <div>
    <form v-on:submit.prevent="searchQuote">
      <div id="box">
        <h2>Please insert an English quote form the Daodejing here:</h2>
        <textarea id="input" name="inputText" rows="20" cols="100" v-model="newQuote.quote" />
        
        <div>
        </div>

        <button v-on:click.prevent="searchQuote" type="button">Check Quote</button>

        <div>
        </div>

        <input v-model="chapter.chapterNumber" type="text" id="chapters" name="chapters" />
        <!-- <button v-on:click="setChapters(chapter.chapterNumber)" type="button">Check Chapter</button> -->
        <router-link v-on:click="setChapters(chapter.chapterNumber)" v-bind:to="{name: 'chapters'}" tag="button">Check Chapters</router-link>
        <h3>
          You may also choose a chapter, here, and compare all the English
          versions of that chapter found in our database.
        </h3>
      </div>
    </form>
  </div>
</template>

<script>
import quoteService from "@/services/QuoteService.js";

export default {
  data() {
    return {
      newQuote: {
        quote: "",
        author: "",
        book: "",
      },
      chapter: {
        chapterNumber: "",
        text: "",
        author: "",
        textId: "",
      }
    };
  },
  methods: {
    searchQuote() {
      quoteService.searchQuote(this.newQuote.quote)
      .then((response) => {
        if (response.status === 200) {
          alert("good job!!");
          this.resetForm();
        }
      });
    },
    resetForm() {
      this.newQuote = {};
    },
    setChapters(chapter) {
      this.$store.commit("SET_CHAPTERS", chapter);
    },
  
}};
</script>

<style>
</style>