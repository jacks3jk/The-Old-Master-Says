<template>
<div>

    <div id="resultBody" v-for="result in results" v-bind:key="result.author">
        <h2>{{result.quote}}</h2>
    </div>

</template>
</div>
<script>
export default {

}
</script>

<style>

</style>