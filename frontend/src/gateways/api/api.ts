import axios from 'axios';

export const api = axios.create({
	baseURL: import.meta.env.PROD ? 'https://www.api.meuporquinho.com/' : 'http://localhost:8080/',
});
