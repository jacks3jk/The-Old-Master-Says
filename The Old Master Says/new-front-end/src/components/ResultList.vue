<template>
<div>

    <div id="resultBody" v-for="result in resultsList" v-bind:key="result.author">

        <h1>{{result.chapterNumber}}</h1>
        <h2>{{result.translator}}</h2>
        <h3>{{result.chapterText}}</h3>
    </div>
</div>
</template>

<script>

import quoteService from "@/services/QuoteService.js";

export default {
    data() {
        return {
            resultsList: [],
        }
    },
    methods: {
        retrieveResults() {
            let textInput = this.$store.state.textResults
            quoteService.searchQuote(textInput).then(response => {
                this.resultsList = response.data;
            }
            )
        }
    },
    created() {
        this.retrieveResults();
    },

}
</script>

<style>

</style>