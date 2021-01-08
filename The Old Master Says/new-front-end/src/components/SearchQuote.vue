<template>
  <div>
    <form v-on:submit.prevent="searchQuote">
      <div id="box">
        <h2>Please insert an English quote form the Daodejing here:</h2>

        
        <textarea id="input" name="inputText" rows="20" cols="100" v-model="newQuote.searchText" />
        
        <div>
        </div>

        <!-- <router-link v-on:click="setText(newQuote.searchText)" v-bind:to="{name: 'result'}">Check Quote</router-link> -->
        <button type="button" v-on:click="setText(newQuote.searchText)">Submit</button>

        <div>
        </div>

        <input v-model="chapter.chapterNumber" type="text" id="chapters" name="chapters" />
        <button v-on:click="setChapters(chapter.chapterNumber)" type="button">Check Chapter</button>
        <!-- <router-link v-on:click="setChapters(chapter.chapterNumber)" v-bind:to="{name: 'chapters'}" tag="button">Check Chapters</router-link> -->
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
        textId: "",
        searchText: "",
        text: "",
        author: "",
        publishYear: "",
      },
      chapter: {
        chapterNumber: "",
        text: "",
        author: "",
        textId: "",
        publishYear: "",
      }
    };
  },
  methods: {
    searchQuote() {
      quoteService.searchQuote(this.newQuote.searchText)
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
      this.$router.push({name: 'chapters'})
    },
    setText(text) {
      this.$store.commit("SET_TEXT", text);
      this.$router.push({name: 'result'});
    }
  
}};
</script>

<style>
</style>