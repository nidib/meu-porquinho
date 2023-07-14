import { CaretRight, Plus } from '@phosphor-icons/react';

type Props = {
	title: string;
	children: React.ReactNode;
	onPlusClick?: () => void;
	onArrowClick?: () => void;
};

export function Section(props: Props) {
	const { children, title, onPlusClick, onArrowClick } = props;

	return (
		<div>
			<div className="flex items-center justify-between py-6 text-xl font-bold text-white">
				<p className="uppercase">{title}</p>
				<div className="flex gap-5">
					{onPlusClick ? (
						<a onClick={() => onPlusClick()}>
							<Plus weight="bold" size={'1.75rem'} />
						</a>
					) : null}
					{onArrowClick ? (
						<a onClick={() => onArrowClick()}>
							<CaretRight weight="bold" size={'1.75rem'} />
						</a>
					) : null}
				</div>
			</div>
			{children}
		</div>
	);
}
