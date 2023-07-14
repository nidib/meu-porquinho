import clsx from 'clsx';
import { LoadingSpinner } from 'src/components/loading-spinner';

import { MetaTheme } from 'src/components/meta-theme';
import { decimalToBRL } from 'src/utils/currency';

type Props = {
	income: number;
	expense: number;
	loading?: boolean;
};

export function Header(props: Props) {
	const { income, expense, loading = false } = props;
	const hasIncome = income !== 0;
	const hasExpense = expense !== 0;

	return (
		<>
			<MetaTheme color="rosa-150" />
			<div className="rounded-b-3xl bg-rosa-porquinho-150 p-7 ">
				<h1 className="mb-8 text-lg">
					MEU<strong>PORQUINHO</strong>
				</h1>
				<div className="flex flex-col items-center">
					<h2 className="mb-2 font-semibold">SALDO ATUAL</h2>
					<div
						className={clsx([
							'bg-ceu-noite-200',
							'flex',
							'gap-4',
							'rounded-full',
							'py-4',
							'px-6',
							'justify-around',
							'items-center',
							'font-semibold',
						])}
					>
						{loading ? (
							<LoadingSpinner />
						) : (
							<>
								{hasIncome || hasExpense ? (
									<>
										<p className={clsx([hasIncome ? 'text-verde-100' : 'text-white opacity-20'])}>
											{hasIncome ? decimalToBRL(income) : '-'}
										</p>
										<p className="text-white opacity-20">|</p>
										<p
											className={clsx([
												hasExpense ? 'text-vermelho-100' : 'text-white opacity-20',
											])}
										>
											{hasExpense ? decimalToBRL(expense) : '-'}
										</p>
									</>
								) : (
									<p className="text-white opacity-20">Nenhum saldo.</p>
								)}
							</>
						)}
					</div>
				</div>
			</div>
		</>
	);
}
