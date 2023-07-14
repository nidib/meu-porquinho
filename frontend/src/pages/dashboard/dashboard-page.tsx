import { BorderedCard } from 'src/components/bordered-card';
import { FullStarBg } from 'src/components/full-star-bg';
import { Section } from 'src/components/section';
import { BarChart } from 'src/components/charts/bar';
import { PieChart } from 'src/components/charts/pie';
import { AccountSummaryContainer } from 'src/components/account-summary/account-summary-container';
import { HeaderContainer } from 'src/components/header/header-container';
import { CategoriesSliderContainer } from 'src/components/categories-slider/categories-slider-container';

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
			<HeaderContainer />
			<div className="mt-3 flex flex-col gap-4 pb-10">
				<div className="px-6">
					<Section title="Contas" onPlusClick={noop} onArrowClick={noop}>
						<BorderedCard>
							<AccountSummaryContainer />
						</BorderedCard>
					</Section>
				</div>
				<div className="px-6">
					<Section title="Categorias" onPlusClick={noop} onArrowClick={noop}>
						<CategoriesSliderContainer />
					</Section>
				</div>
				<div className="px-6">
					<Section title="GASTOS GERAIS">
						<div className="flex flex-col gap-6">
							<BorderedCard title="Gastos gerais" notAvailable>
								<div className="select-none opacity-70 blur-sm">
									<BarChart data={barData} />
								</div>
							</BorderedCard>
							<BorderedCard title="Gastos por categoria" notAvailable>
								<div className="z-0 select-none opacity-70 blur-sm">
									<PieChart data={pieData} />
								</div>
							</BorderedCard>
						</div>
					</Section>
				</div>
			</div>
		</FullStarBg>
	);
}
