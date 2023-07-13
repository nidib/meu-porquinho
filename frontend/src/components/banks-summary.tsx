import clsx from 'clsx';
import { decimalToBRL } from 'src/utils/currency';

type Props = {
	banks: { name: string; balance: number }[];
};

export function BanksSummary(props: Props) {
	const { banks } = props;

	return (
		<ul className="flex flex-col gap-1 text-lg text-white">
			{banks.map((bank) => (
				<li key={bank.name} className="flex justify-between">
					<div>{bank.name}</div>
					<div className={clsx(['font-semibold', bank.balance > 0 ? 'text-verde-100' : 'text-vermelho-100'])}>
						{decimalToBRL(bank.balance)}
					</div>
				</li>
			))}
		</ul>
	);
}
