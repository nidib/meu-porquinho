import type { Icon } from '@phosphor-icons/react';
import {
	Bus,
	CurrencyBtc,
	Dog,
	DotsThreeOutline,
	ForkKnife,
	GraduationCap,
	Heartbeat,
	House,
	Martini,
	Money,
	Popcorn,
	Scissors,
	ShoppingBag,
	ShoppingCartSimple,
	TreePalm,
	UsersFour,
	YoutubeLogo,
} from '@phosphor-icons/react';
import { useEffect, useState } from 'react';
import { CategoriesSlider } from 'src/components/categories-slider';
import { getCategories } from 'src/gateways/api/usecases/get-categories';

export type CategoryTypes = 'GASTO' | 'RECEITA';

export type Category = {
	id: string;
	name: string;
	type: CategoryTypes;
};

const categoryIconByName: Record<string, Icon | undefined> = {
	'Cuidados pessoais': Scissors,
	'Dívidas e empréstimos': Money,
	'Assinaturas e serviços': YoutubeLogo,
	'Bares e restaurantes': Martini,
	Alimentação: ForkKnife,
	Casa: House,
	Compras: ShoppingBag,
	Educação: GraduationCap,
	Família: UsersFour,
	Investimentos: CurrencyBtc,
	Lazer: Popcorn,
	Mercado: ShoppingCartSimple,
	Outros: DotsThreeOutline,
	Pets: Dog,
	Saúde: Heartbeat,
	Transporte: Bus,
	Viagem: TreePalm,
};

export function CategoriesSliderContainer() {
	const [categories, setCategories] = useState<Category[]>([]);

	useEffect(() => {
		getCategories()
			.then((c) =>
				setCategories(
					c
						.map((category) => ({ id: category.id, name: category.nome, type: category.tipo }))
						.filter((c) => c.type === 'GASTO')
				)
			)
			.catch(console.log);
	}, []);

	return (
		<CategoriesSlider
			categories={categories.map((c) => {
				const icon = categoryIconByName[c.name];

				return { ...c, icon: icon ?? DotsThreeOutline };
			})}
		/>
	);
}
