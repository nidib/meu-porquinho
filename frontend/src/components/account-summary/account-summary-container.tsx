import { useEffect, useState } from 'react';

import { AccountSummary } from 'src/components/account-summary/account-summary';
import { LoadingSpinner } from 'src/components/loading-spinner';
import { getAccountsSummaries } from 'src/gateways/api/usecases/get-accounts-summaries';

export type AccountSummary = {
	id: string;
	title: string;
	balance: number;
};

export function AccountSummaryContainer() {
	const [accountsSummaries, setAccountsSummaries] = useState<AccountSummary[]>([]);
	const [loading, setLoading] = useState(false);

	useEffect(() => {
		setLoading(true);
		getAccountsSummaries()
			.then((summaries) =>
				setAccountsSummaries(summaries.map((s) => ({ id: s.id, title: s.nome, balance: s.saldo })))
			)
			.catch(console.log)
			.finally(() => setLoading(false));
	}, []);

	if (loading) {
		return <LoadingSpinner />;
	}

	return <AccountSummary accounts={accountsSummaries} />;
}
