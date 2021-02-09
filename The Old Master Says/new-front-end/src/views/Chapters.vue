<template>
<div>
    <div>
       <h1>Results for Chapter {{$store.state.chapters}}</h1> 
    </div>
    <div v-for="chapter in chaptersList" v-bind:key="chapter.textId">
        <div>
            <h2>{{chapter.author}}</h2>
            <h4>{{chapter.publishYear}}</h4>
           <h3>{{chapter.chapterText}}</h3> 
        </div>
    </div>
  </div>
</template>

<script>


import quoteService from "@/services/QuoteService.js";

export default {
    data() {
        return {
            // chapterNumber: 0,
            // chapterText: "",
            // author: "",
            // textId: "",
            // publishYear: 0,
            chaptersList: [],
        }
    },
    props: ["chapters"],
    methods: {
        retrieveChapters() {
            let selectedChapter = this.$store.state.chapters
            quoteService.getChapters(selectedChapter).then(response => {
                this.chaptersList = response.data;
                // this.chapterNumber = response.data.chapterNumber;
                // this.chapterText = response.data.chapterText;
                // this.author = response.data.author;
                // this.textId = response.data.textId;
                // this.publishYear = response.data.publishYear;
            }
            )
        }
    },
    created() {
        this.retrieveChapters();
    },
    computed: {
        // list() {
        //     //let currentChapter = Object.keys(this.$store.state.chapters);
        //     return this.chapters.filter((chapter) => chapter.chapterNumber === this.$store.state.chapters);
        // },
    }
}  
</script>


<style>
</style>