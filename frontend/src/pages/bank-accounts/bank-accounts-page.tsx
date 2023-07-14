import { AccountSummaryContainer } from 'src/components/account-summary/account-summary-container';
import { BorderedCard } from 'src/components/bordered-card';
import { FullStarBg } from 'src/components/full-star-bg';
import { HeaderContainer } from 'src/components/header/header-container';
import { MovementsContainer } from 'src/components/movements/movements-container';
import { Section } from 'src/components/section';

export function BankAccountsPage() {
	return (
		<FullStarBg hideStars>
			<HeaderContainer />
			<div className="mt-3 flex flex-col gap-4 pb-10">
				<div className="px-6">
					<Section title="Contas">
						<div className="flex flex-col gap-6">
							<BorderedCard title="Saldos">
								<AccountSummaryContainer />
							</BorderedCard>
							<BorderedCard title="Movimentações">
								<MovementsContainer />
							</BorderedCard>
						</div>
					</Section>
				</div>
			</div>
		</FullStarBg>
	);
}
