import { CategoryTypes } from 'src/components/categories-slider/categories-slider-container';
import { api } from 'src/gateways/api/api';

type GetMovementRes = {
	id: string;
	nome: string;
	valor: number;
	data: string;
	dataDeConclusao: string | null;
	contaBancaria: {
		id: string;
		titulo: string;
		saldo: number;
		diaDoVencimentoDaFatura: number;
	};
	categoria: {
		id: string;
		nome: string;
		tipo: CategoryTypes;
	};
}[];

export async function getMovements(): Promise<GetMovementRes> {
	return api.get<GetMovementRes>('/api/movimentacao').then((response) => response.data);
}
