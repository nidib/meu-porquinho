import porquinho from '../assets/porquinho.png';

export function LogoHeading() {
	return (
		<h1 className="text-4xl font-light text-rosa-porquinho-100">
			{'MEU'}
			<span className="font-bold">{'PORQUINHO'}</span>
		</h1>
	);
}

export function Logo() {
	return (
		<img
			src={porquinho}
			className="h-[330px]"
			alt="Logo do site. Um porquinho com traje de astronauta e uma moeda dourada"
		/>
	);
}
