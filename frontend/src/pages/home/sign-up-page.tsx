import { CircleNotch } from '@phosphor-icons/react';
import { useState } from 'react';

import { Button } from 'src/components/button';
import { FullStarBg } from 'src/components/full-star-bg';
import { Input } from 'src/components/input';
import { wait } from 'src/pages/home/home-page';

export function SignUpPage() {
	const [isLoading, setIsLoading] = useState(false);

	const handleButtonClick = async () => {
		setIsLoading(true);
		await wait(2000);
		setIsLoading(false);
	};

	return (
		<FullStarBg>
			<div className="container mx-auto px-10 pt-10 md:max-w-[600px]">
				<form className="flex flex-col gap-10" onSubmit={(e) => e.preventDefault()}>
					<Input id="full-name" type="text" placeholder="Nome completo" />
					<Input id="birthday" type="text" placeholder="Data de nascimento" />
					<Input id="user" type="text" placeholder="Nome de usuário" />
					<Input id="email" type="email" placeholder="E-mail" />
					<Input id="email-confirmation" type="email" placeholder="Confirmar e-mail" />
					<Input id="password" type="password" placeholder="Password" />
					<Button
						intent={'primary'}
						disabled={isLoading}
						onClick={handleButtonClick}
						type={'submit'}
						fullWidth
					>
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
