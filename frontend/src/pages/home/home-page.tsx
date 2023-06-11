import clsx from 'clsx';
import { useState } from 'react';
import { CircleNotch, LockSimple, User } from '@phosphor-icons/react';

import { Button } from 'src/components/button';
import { Input } from 'src/components/input';
import { Logo, LogoHeading } from 'src/components/logo';
import trail from '../../assets/trail.png';

const wait = (ms: number) => new Promise((res) => setTimeout(res, ms));

export function HomePage() {
	const [isLoading, setIsLoading] = useState(false);

	const handleButtonClick = async () => {
		setIsLoading(true);
		await wait(2000);
		setIsLoading(false);
	};

	return (
		<main
			className={clsx(
				'flex',
				'h-screen',
				'w-screen',
				'flex-col',
				'md:flex-row',
				'items-center',
				'justify-center',
				'md:justify-around',
				'gap-10',
				'bg-ceu-noite-100',
				'px-12',
				'md:px-8',
				'overflow-hidden'
			)}
		>
			<div className="flex flex-col items-center gap-1 md:flex-col-reverse md:gap-12 lg:flex-row-reverse lg:items-start">
				<div className="relative">
					<div className="md:rotate-[30deg]">
						<Logo />
					</div>
					<img src={trail} className="absolute right-[120px] top-[300px] hidden md:block" />
				</div>
				<div className="flex flex-col gap-16">
					<div>
						<LogoHeading />
						<h2 className="hidden text-lg text-gray-50 md:block">Suas finanças sob controle</h2>
					</div>
					<p className="hidden font-thin text-gray-50 lg:block">
						Se você ainda não tem uma conta,
						<br /> registre-se{' '}
						<a
							href="#"
							className="font-normal text-rosa-porquinho-100 underline hover:text-rosa-porquinho-200"
						>
							aqui!
						</a>
					</p>
				</div>
			</div>
			<form className="flex w-full flex-col gap-10 md:max-w-[350px]" onSubmit={(e) => e.preventDefault()}>
				<div className="flex w-full flex-col gap-6">
					<Input id="user" leftIcon={User} type="text" placeholder="Login ou e-mail" />
					<Input id="password" leftIcon={LockSimple} type="password" placeholder="Senha" />
				</div>
				<div className="flex w-full flex-col gap-6">
					<Button
						intent={'primary'}
						disabled={isLoading}
						onClick={handleButtonClick}
						type={'submit'}
						fullWidth
					>
						<div className="flex h-6 items-center justify-center">
							{isLoading ? <CircleNotch weight="bold" className="animate-spin text-2xl" /> : 'Entrar'}
						</div>
					</Button>
					<Button intent={'ghost'}>Esqueci a senha</Button>
				</div>
			</form>
		</main>
	);
}
