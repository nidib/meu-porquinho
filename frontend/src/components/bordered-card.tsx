type Props = {
	children: React.ReactNode;
	title?: string;
};

export function BorderedCard(props: Props) {
	const { children, title } = props;

	return (
		<div className="flex flex-col gap-6 rounded-2xl border-2 border-white border-opacity-20 p-6 shadow-md">
			{title ? <p className="text-center text-lg font-semibold text-white text-opacity-40">{title}</p> : null}
			{children}
		</div>
	);
}
