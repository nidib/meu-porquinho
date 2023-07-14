import { useEffect, useState } from 'react';
import { Header } from 'src/components/header/header';
import { getIncomeExpenseSummary } from 'src/gateways/api/usecases/get-income-expense-summary';

type IcomeExpenseSummary = {
	income: number;
	expense: number;
};

export function HeaderContainer() {
	const [incomeExpenseSummary, setIncomeExpenseSummary] = useState<IcomeExpenseSummary>({ income: 0, expense: 0 });
	const [loading, setLoading] = useState(true);

	useEffect(() => {
		setLoading(true);
		getIncomeExpenseSummary()
			.then((ie) => setIncomeExpenseSummary({ income: ie.ganhos, expense: ie.gastos }))
			.catch(console.log)
			.finally(() => setLoading(false));
	}, []);

	return <Header income={incomeExpenseSummary.income} expense={incomeExpenseSummary.expense} loading={loading} />;
}
