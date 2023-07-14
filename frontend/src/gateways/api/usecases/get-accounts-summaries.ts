import { api } from 'src/gateways/api/api';

type GetAccountsSummariesRes = {
	id: string;
	nome: string;
	saldo: number;
}[];

export async function getAccountsSummaries(): Promise<GetAccountsSummariesRes> {
	return api.get<GetAccountsSummariesRes>('/api/conta-bancaria/resumo').then((response) => response.data);
}
