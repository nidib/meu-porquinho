import clsx from 'clsx';
import { decimalToBRL } from 'src/utils/currency';

type Props = {
	data: { title: string; amount: number }[];
};

export function BarChart(props: Props) {
	const { data } = props;
	const sorted = data.map((item) => Math.abs(item.amount)).sort((a, b) => (a > b ? 1 : -1));
	const min = sorted[0];
	const max = sorted[sorted.length - 1];
	const greenPercentage = Math.ceil((min / max) * 100);

	return (
		<div className="flex flex-col gap-5">
			<div className="flex flex-col gap-1">
				<div className="flex justify-center">
					<div className="w-[3px] rounded-full bg-white opacity-10" />
					<div className="flex w-[90%] flex-col gap-2 px-10 py-5">
						{data.map((item) => (
							<div
								key={item.title}
								className={clsx([
									'h-[40px]',
									'rounded-lg',
									item.amount > 0 ? 'bg-verde-100' : 'bg-vermelho-100',
								])}
								style={{ width: item.amount < 0 ? '100%' : `${greenPercentage}%` }}
							/>
						))}
					</div>
				</div>
				<p className="text-center lowercase text-white text-opacity-40">jun</p>
			</div>
			<ul className="flex justify-around text-center text-lg">
				{data.map((item) => (
					<li key={item.title}>
						<p
							className={clsx([
								'font-semibold',
								item.amount > 0 ? 'text-verde-100' : 'text-vermelho-100',
							])}
						>
							{decimalToBRL(item.amount)}
						</p>
						<p className="text-white text-opacity-40">{item.title}</p>
					</li>
				))}
			</ul>
		</div>
	);
}
