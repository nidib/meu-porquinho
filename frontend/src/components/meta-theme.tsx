import { Helmet } from 'react-helmet';

type Props = {
	color: 'rosa-150' | 'ceu-noite-100';
};

const colors: Record<Props['color'], string> = {
	'rosa-150': '#FF99DD',
	'ceu-noite-100': '#121F25',
};

export function MetaTheme(props: Props) {
	return (
		<Helmet>
			<meta name="theme-color" content={colors[props.color]} />
		</Helmet>
	);
}
