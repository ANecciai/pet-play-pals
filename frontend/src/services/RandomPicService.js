import axios from 'axios';

const http = axios.create({
    baseURL: "https://dog.ceo/api/breeds/image/random"
});
export default {
    picture() {
        //returning
        return http.get('/');
    }
}
