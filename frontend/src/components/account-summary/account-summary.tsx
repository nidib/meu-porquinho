import clsx from 'clsx';

import type { AccountSummary } from 'src/components/account-summary/account-summary-container';
import { decimalToBRL } from 'src/utils/currency';

type Props = {
	accounts: AccountSummary[];
	emptyMessage: string;
};

export function AccountSummary(props: Props) {
	const { accounts, emptyMessage } = props;

	return (
		<ul className="flex flex-col gap-1 text-lg text-white">
			{accounts.length === 0 ? <p className="text-center text-white opacity-20">{emptyMessage}</p> : null}
			{accounts.map((account) => (
				<li key={account.title} className="flex justify-between">
					<p>{account.title}</p>
					<p
						className={clsx([
							'font-semibold',
							account.balance > 0 ? 'text-verde-100' : 'text-vermelho-100',
						])}
					>
						{decimalToBRL(account.balance)}
					</p>
				</li>
			))}
		</ul>
	);
}
