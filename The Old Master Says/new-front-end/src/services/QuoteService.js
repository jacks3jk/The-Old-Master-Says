// @ts-nocheck

import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:3000"
});

export default {
    searchQuote(searchText) {
        return http.get('/quote?searchText=' + searchText) 
    },

    getChapters(number) {
        return http.get('/translations?chapterNumber=' + number)
    },
}