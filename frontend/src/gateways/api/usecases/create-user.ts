import { api } from 'src/gateways/api/api';

type CreateUserReq = {
	login: string;
	email: string;
	senha: string;
	nomeCompleto: string;
	dataDeNascimento: string;
};

type CreateUserRes = {
	id: string;
	login: string;
	email: string;
};

export async function createUser(dados: CreateUserReq): Promise<CreateUserRes> {
	return api.post<CreateUserRes>('/api/usuario', dados).then((response) => response.data);
}
