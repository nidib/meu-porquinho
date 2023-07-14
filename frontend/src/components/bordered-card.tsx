type Props = {
	children: React.ReactNode;
	title?: string;
	notAvailable?: boolean;
};

export function BorderedCard(props: Props) {
	const { children, title, notAvailable = false } = props;

	return (
		<div className="flex flex-col gap-6 rounded-2xl border-2 border-white border-opacity-20 p-6 shadow-md">
			{title ? (
				<p className="text-center text-lg font-semibold text-white text-opacity-40">
					{title}{' '}
					{notAvailable && (
						<span className="ml-3 rounded-full bg-rosa-porquinho-100 p-1 text-sm text-ceu-noite-200">
							Em breve
						</span>
					)}
				</p>
			) : null}
			{children}
		</div>
	);
}
