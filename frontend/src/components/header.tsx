import clsx from 'clsx';

import { MetaTheme } from 'src/components/meta-theme';
import { decimalToBRL } from 'src/utils/currency';

export function Header() {
	return (
		<>
			<MetaTheme color="rosa-150" />
			<div className="rounded-b-3xl bg-rosa-porquinho-150 p-7 ">
				<h1 className="mb-8 text-lg">
					MEU<strong>PORQUINHO</strong>
				</h1>
				<div className="flex flex-col items-center">
					<h2 className="mb-2 font-semibold">SALDO ATUAL</h2>
					<div
						className={clsx([
							'bg-ceu-noite-200',
							'flex',
							'w-[70%]',
							'rounded-full',
							'p-4',
							'justify-around',
							'items-center',
							'font-semibold',
						])}
					>
						<p className={clsx('text-verde-100')}>{decimalToBRL(5793.75)}</p>
						<p className="text-white opacity-20">|</p>
						<p className={clsx('text-vermelho-100')}>{decimalToBRL(621.9)}</p>
					</div>
				</div>
			</div>
		</>
	);
}
