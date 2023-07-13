import clsx from 'clsx';
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { CircleNotch, LockSimple, User } from '@phosphor-icons/react';
import { useForm } from 'react-hook-form';

import { Button } from 'src/components/button';
import { Input } from 'src/components/input';
import { Logo, LogoHeading } from 'src/components/logo';
import { requiredField } from 'src/pages/sign-up/sign-up-page';
import { login } from 'src/gateways/api/usecases/login';
import { MetaTheme } from 'src/components/meta-theme';

export function HomePage() {
	const [isLoading, setIsLoading] = useState(false);
	const navigate = useNavigate();
	const {
		register,
		formState: { errors },
		getValues,
	} = useForm({
		defaultValues: {
			loginOrEmail: '',
			password: '',
		},
	});

	const handleButtonClick = async () => {
		const { loginOrEmail, password } = getValues();

		try {
			setIsLoading(true);
			const { token } = await login({ loginOuEmail: loginOrEmail, senha: password });
			localStorage.setItem('token', token);
			navigate('/home');
		} catch (e) {
			console.log(e);
		} finally {
			setIsLoading(false);
		}
	};

	return (
		<>
			<MetaTheme color="ceu-noite-100" />
			<main
				className={clsx([
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
					'overflow-hidden',
					'bg-stars',
					'bg-center',
					'bg-cover',
					'bg-repeat-y',
				])}
			>
				<div className="flex flex-col items-center gap-1 md:flex-col-reverse md:gap-12 lg:flex-row-reverse lg:items-start">
					<div className="md:rotate-[30deg]">
						<Logo />
					</div>
					<div className="flex flex-col gap-16">
						<div>
							<LogoHeading />
							<h2 className="hidden text-lg text-gray-50 md:block">Suas finanças sob controle</h2>
						</div>
						<p className="hidden font-thin text-gray-50 lg:block">
							Se você ainda não tem uma conta,
							<br /> registre-se{' '}
							<Link
								to={'/criar-conta'}
								className="font-normal text-rosa-porquinho-100 underline hover:text-rosa-porquinho-200"
							>
								aqui!
							</Link>
						</p>
					</div>
				</div>
				<form className="flex w-full flex-col gap-10 md:max-w-[350px]" onSubmit={(e) => e.preventDefault()}>
					<div className="flex w-full flex-col gap-6">
						<Input
							id="user"
							leftIcon={User}
							type="text"
							placeholder="Login ou e-mail"
							errorState={errors.loginOrEmail?.message}
							{...register('loginOrEmail', { ...requiredField })}
						/>
						<Input
							id="password"
							leftIcon={LockSimple}
							type="password"
							placeholder="Senha"
							errorState={errors.password?.message}
							{...register('password', { ...requiredField })}
						/>
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
						<Link to={'/criar-conta'}>
							<Button>Criar conta</Button>
						</Link>
					</div>
				</form>
			</main>
		</>
	);
}
