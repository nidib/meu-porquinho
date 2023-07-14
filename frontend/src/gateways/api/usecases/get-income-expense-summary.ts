import { api } from 'src/gateways/api/api';

type GetIncomeExpenseSummaryRes = {
	ganhos: number;
	gastos: number;
};

export async function getIncomeExpenseSummary(): Promise<GetIncomeExpenseSummaryRes> {
	return api.get<GetIncomeExpenseSummaryRes>('/api/conta-bancaria/saldo').then((response) => response.data);
}
