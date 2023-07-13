import { CircleNotch } from '@phosphor-icons/react';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import { parse, format } from 'date-fns';

import { Button } from 'src/components/button';
import { FullStarBg } from 'src/components/full-star-bg';
import { Input } from 'src/components/input';
import { createUser } from 'src/gateways/api/usecases/create-user';

export const requiredField = {
	required: {
		value: true,
		message: 'Campo obrigatório',
	},
};

export function SignUpPage() {
	const [isLoading, setIsLoading] = useState(false);
	const navigate = useNavigate();
	const { register, formState, handleSubmit, getValues } = useForm({
		defaultValues: {
			fullName: '',
			birthday: '',
			username: '',
			email: '',
			emailConfirmation: '',
			password: '',
			passwordConfirmation: '',
		},
	});

	const onSubmit = async () => {
		const { fullName, birthday, username, email, password } = getValues();

		try {
			const ISOBirthday = format(parse(birthday, 'dd/MM/yyyy', new Date()), 'yyyy-MM-dd');

			setIsLoading(true);
			await createUser({
				nomeCompleto: fullName,
				dataDeNascimento: ISOBirthday,
				login: username,
				senha: password,
				email,
			});
			navigate('/');
		} catch (e) {
			console.log(e);
		} finally {
			setIsLoading(false);
		}
	};

	return (
		<FullStarBg>
			<div className="container mx-auto p-10 md:max-w-[600px]">
				<form className="flex flex-col gap-10" onSubmit={handleSubmit(onSubmit)}>
					<Input
						id="full-name"
						type="text"
						placeholder="* Nome completo"
						errorState={formState.errors.fullName?.message}
						{...register('fullName', { ...requiredField })}
					/>
					<Input
						id="birthday"
						type="text"
						placeholder="* Data de nascimento"
						errorState={formState.errors.birthday?.message}
						{...register('birthday', { ...requiredField })}
					/>
					<Input
						id="username"
						type="text"
						placeholder="* Nome de usuário"
						errorState={formState.errors.username?.message}
						{...register('username', { ...requiredField })}
					/>
					<Input
						id="email"
						type="email"
						placeholder="* E-mail"
						errorState={formState.errors.email?.message}
						{...register('email', { ...requiredField })}
					/>
					<Input
						id="email-confirmation"
						type="email"
						placeholder="* Confirmar e-mail"
						errorState={formState.errors.emailConfirmation?.message}
						{...register('emailConfirmation', {
							...requiredField,
							validate: (value, form) => value === form.email || 'Os e-emails estão diferentes',
						})}
					/>
					<Input
						id="password"
						type="password"
						placeholder="* Senha"
						errorState={formState.errors.password?.message}
						{...register('password')}
					/>
					<Input
						id="password-confirmation"
						type="password"
						placeholder="* Confirmar senha"
						errorState={formState.errors.passwordConfirmation?.message}
						{...register('passwordConfirmation', {
							...requiredField,
							validate: (value, form) => value === form.password || 'As senhas estão diferentes',
						})}
					/>
					<Button intent={'primary'} disabled={isLoading} type={'submit'} fullWidth>
						<div className="flex h-6 items-center justify-center">
							{isLoading ? (
								<CircleNotch weight="bold" className="animate-spin text-2xl" />
							) : (
								'Criar conta'
							)}
						</div>
					</Button>
				</form>
			</div>
		</FullStarBg>
	);
}
