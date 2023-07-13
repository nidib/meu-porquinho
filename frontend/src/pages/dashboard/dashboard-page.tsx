import { Dog, ForkKnife, Popcorn, TreePalm } from '@phosphor-icons/react';

import { BanksSummary } from 'src/components/banks-summary';
import { BorderedCard } from 'src/components/bordered-card';
import { CategoriesSlider } from 'src/components/categories-slider';
import { FullStarBg } from 'src/components/full-star-bg';
import { Header } from 'src/components/header';
import { Section } from 'src/components/section';
import { BarChart } from 'src/components/charts/bar';
import { PieChart } from 'src/components/charts/pie';

const banks = [
	{ name: 'Itaú', balance: 27.83 },
	{ name: 'Nubank', balance: 1700.3 },
	{ name: 'Bradesco', balance: -146.94 },
];

const categories = [
	{ name: 'Animais', icon: Dog },
	{ name: 'Alimentação', icon: ForkKnife },
	{ name: 'Viagem', icon: TreePalm },
	{ name: 'Lazer', icon: Popcorn },
];

const barData = [
	{ title: 'Ganhos', amount: 27.83 },
	{ title: 'Gastos', amount: -146.94 },
];

const pieData = [
	{ title: 'Animais', amount: 25.63, color: '#FFE063' },
	{ title: 'Alimentação', amount: 84.57, color: '#40E1E1' },
	{ title: 'Viagem', amount: 36.74, color: '#FF63DD' },
];

// eslint-disable-next-line @typescript-eslint/no-empty-function
const noop = () => {};

export function DashboardPage() {
	return (
		<FullStarBg hideStars>
			<Header />
			<div className="mt-3 flex flex-col gap-4 pb-10">
				<div className="px-6">
					<Section title="Contas" onPlusClick={noop} onArrowClick={noop}>
						<BorderedCard>
							<BanksSummary banks={banks} />
						</BorderedCard>
					</Section>
				</div>
				<div className="px-6">
					<Section title="Categorias" onPlusClick={noop} onArrowClick={noop}>
						<CategoriesSlider categories={categories} />
					</Section>
				</div>
				<div className="px-6">
					<Section title="GASTOS GERAIS" onArrowClick={noop}>
						<div className="flex flex-col gap-6">
							<BorderedCard title="Gastos gerais">
								<BarChart data={barData} />
							</BorderedCard>
							<BorderedCard title="Gastos por categoria">
								<PieChart data={pieData} />
							</BorderedCard>
						</div>
					</Section>
				</div>
			</div>
		</FullStarBg>
	);
}
