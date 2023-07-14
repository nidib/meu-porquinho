import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import { CircleNotch } from '@phosphor-icons/react';

import { Button } from 'src/components/button';
import { FullStarBg } from 'src/components/full-star-bg';
import { Input } from 'src/components/input';
import { Section } from 'src/components/section';
import { createBankAccount } from 'src/gateways/api/usecases/create-bank-account';
import { requiredField } from 'src/pages/sign-up/sign-up-page';

export function CreateBankAccountPage() {
	const [isLoading, setIsLoading] = useState(false);
	const navigate = useNavigate();
	const { register, formState, handleSubmit, getValues } = useForm({
		defaultValues: {
			title: '',
			balance: '',
			dueDay: '',
		},
	});

	const onSubmit = async () => {
		const { title, balance, dueDay } = getValues();

		try {
			setIsLoading(true);
			await createBankAccount({
				titulo: title,
				saldo: Number(balance.replace(',', '.')),
				diaDoVencimentoDaFatura: Number(dueDay),
			});
			navigate(-1);
		} catch (e) {
			console.log(e);
		} finally {
			setIsLoading(false);
		}
	};

	return (
		<FullStarBg hideStars>
			<div className="container mx-auto p-10 md:max-w-[600px]">
				<Section title="Criar conta bancária">
					<form className="flex flex-col gap-10" onSubmit={handleSubmit(onSubmit)}>
						<Input
							id="name"
							type="text"
							placeholder="* Nome"
							keyType=""
							errorState={formState.errors.title?.message}
							{...register('title', { ...requiredField })}
						/>
						<Input
							id="balance"
							type="number"
							step=".01"
							placeholder="* Saldo"
							errorState={formState.errors.balance?.message}
							{...register('balance', {
								...requiredField,
							})}
						/>
						<Input
							id="dueDay"
							type="number"
							min="1"
							max="28"
							placeholder="* Dia do vencimento da fatura"
							errorState={formState.errors.dueDay?.message}
							{...register('dueDay', {
								...requiredField,
								min: { value: 1, message: 'Dia deve estar entre 5 e 28' },
								max: { value: 28, message: 'Dia deve estar entre 5 e 28' },
							})}
						/>
						<Button intent={'primary'} disabled={isLoading} type={'submit'} fullWidth>
							<div className="flex h-6 items-center justify-center">
								{isLoading ? <CircleNotch weight="bold" className="animate-spin text-2xl" /> : 'Salvar'}
							</div>
						</Button>
					</form>
				</Section>
			</div>
		</FullStarBg>
	);
}
