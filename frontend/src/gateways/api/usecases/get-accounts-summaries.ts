import { api } from 'src/gateways/api/api';

type GetAccountsSummariesRes = {
	id: string;
	nome: string;
	saldo: number;
}[];

export async function getAccountsSummaries(): Promise<GetAccountsSummariesRes> {
	return api
		.get<GetAccountsSummariesRes>('/api/conta-bancaria/resumo', {
			headers: {
				Authorization: `Bearer ${localStorage.getItem('token') ?? ''}`,
			},
		})
		.then((response) => response.data);
}
