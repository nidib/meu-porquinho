import clsx from 'clsx';

type Props = {
	children: React.ReactNode;
};

export function FullStarBg(props: Props) {
	const { children } = props;

	return (
		<div
			className={clsx([
				'min-h-screen',
				'w-screen',
				'bg-ceu-noite-100',
				'bg-stars',
				'bg-center',
				'bg-cover',
				'bg-repeat-y',
			])}
		>
			{children}
		</div>
	);
}
