import { api } from 'src/gateways/api/api';

type LoginReq = {
	loginOuEmail: string;
	senha: string;
};

type LoginRes = {
	token: string;
};

export async function login(dados: LoginReq): Promise<LoginRes> {
	return api.post<LoginRes>('/api/login', dados).then((response) => response.data);
}
