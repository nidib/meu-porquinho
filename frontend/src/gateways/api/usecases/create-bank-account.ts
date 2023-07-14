import { api } from 'src/gateways/api/api';

type CreateBankAccountReq = {
	titulo: string;
	saldo: number;
	diaDoVencimentoDaFatura: number;
};

type CreateBankAccountRes = {
	id: string;
	titulo: string;
	saldo: number;
	diaDoVencimentoDaFatura: number;
};

export async function createBankAccount(dados: CreateBankAccountReq): Promise<CreateBankAccountRes> {
	return api.post<CreateBankAccountRes>('/api/conta-bancaria', dados).then((response) => response.data);
}
