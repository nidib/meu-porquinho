import axios from 'axios';

export const api = axios.create({
	baseURL: import.meta.env.PROD ? 'https://api.meuporquinho.com/' : 'http://localhost:8080/',
});
