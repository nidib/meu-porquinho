import { CategoryTypes } from 'src/components/categories-slider/categories-slider-container';
import { api } from 'src/gateways/api/api';

type GetCategoriesRes = {
	id: string;
	nome: string;
	tipo: CategoryTypes;
}[];

export async function getCategories(): Promise<GetCategoriesRes> {
	return api.get<GetCategoriesRes>('/api/categoria').then((response) => response.data);
}
