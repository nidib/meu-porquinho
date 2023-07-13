import clsx from 'clsx';

type Props = {
	children: React.ReactNode;
	hideStars?: boolean;
};

export function FullStarBg(props: Props) {
	const { children, hideStars = false } = props;

	return (
		<div
			className={clsx([
				'min-h-screen',
				'w-screen',
				'bg-ceu-noite-100',
				'bg-center',
				'bg-cover',
				'bg-repeat-y',
				{
					'bg-stars': !hideStars,
				},
			])}
		>
			{children}
		</div>
	);
}
