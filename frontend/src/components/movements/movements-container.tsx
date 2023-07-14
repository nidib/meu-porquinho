import { useEffect, useState } from 'react';
import { AccountSummary } from 'src/components/account-summary/account-summary';
import { BorderedCard } from 'src/components/bordered-card';
import { Category } from 'src/components/categories-slider/categories-slider-container';
import { LoadingSpinner } from 'src/components/loading-spinner';
import { getMovements } from 'src/gateways/api/usecases/get-movements';

type Movement = {
	id: string;
	title: string;
	amount: number;
	date: Date;
	endDate: Date | null;
	bankAccount: {
		id: string;
		title: string;
		balance: number;
		dueDay: number;
	};
	category: Category;
};

export function MovementsContainer() {
	const [movements, setMovements] = useState<Movement[]>([]);
	const [loading, setLoading] = useState(true);

	useEffect(() => {
		setLoading(true);
		getMovements()
			.then((m) =>
				setMovements(
					m.map((mv) => ({
						id: mv.id,
						title: mv.nome,
						amount: mv.valor,
						date: new Date(mv.data),
						endDate: mv.dataDeConclusao ? new Date(mv.dataDeConclusao) : null,
						bankAccount: {
							id: mv.contaBancaria.id,
							title: mv.contaBancaria.titulo,
							balance: mv.contaBancaria.saldo,
							dueDay: mv.contaBancaria.diaDoVencimentoDaFatura,
						},
						category: {
							id: mv.categoria.id,
							name: mv.categoria.nome,
							type: mv.categoria.tipo,
						},
					}))
				)
			)
			.catch(console.log)
			.finally(() => setLoading(false));
	}, []);

	if (loading) {
		return <LoadingSpinner />;
	}

	return (
		<AccountSummary
			accounts={movements.map((m) => ({ id: m.id, title: m.title, balance: m.amount }))}
			emptyMessage="Nenhuma movimentação feita."
		/>
	);
}
