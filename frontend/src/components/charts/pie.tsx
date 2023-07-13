import { PieChart as ReactMinimalPieChart } from 'react-minimal-pie-chart';
import { decimalToBRL } from 'src/utils/currency';

type Props = {
	data: { title: string; amount: number; color: string }[];
};

export function PieChart(props: Props) {
	const { data } = props;

	return (
		<div className="flex flex-col gap-5">
			<div className="flex items-center justify-center">
				<ReactMinimalPieChart
					className="flex-[0.5]"
					data={data.map((item) => ({ ...item, value: item.amount }))}
				/>
			</div>
			<ul className="flex justify-around text-center text-lg">
				{data.map((item) => (
					<li key={item.title}>
						<p className="font-semibold" style={{ color: item.color }}>
							{decimalToBRL(item.amount)}
						</p>
						<p className="text-white text-opacity-40">{item.title}</p>
					</li>
				))}
			</ul>
		</div>
	);
}
