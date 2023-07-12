import { Icon } from '@phosphor-icons/react';
import clsx from 'clsx';
import React, { forwardRef } from 'react';

interface Props extends React.HTMLProps<HTMLInputElement> {
	id: string;
	leftIcon?: Icon;
	placeholder: string;
	errorState?: string;
}

export const Input = forwardRef<HTMLInputElement, Props>(function I(props, ref) {
	const { leftIcon: LeftIcon, errorState, ...rest } = props;

	return (
		<div>
			<div
				className={clsx(
					'flex',
					'gap-4',
					'items-center',
					'px-6',
					'py-4',
					'box-border',
					'border-b-2',
					'border-gray-300',
					'text-gray-300',
					'focus-within:text-gray-50',
					'focus-within:border-gray-50',
					'transition-all'
				)}
			>
				<label htmlFor={props.id}>{LeftIcon && <LeftIcon weight="bold" className="flex-0 text-xl" />}</label>
				<input
					className={clsx('flex-1', 'w-full', 'bg-transparent', 'text-lg', 'outline-none')}
					ref={ref}
					{...rest}
				/>
			</div>
			{errorState && (
				<div className={clsx('p-4 pb-0')}>
					<p className={clsx('text-rose-400')}>{errorState}</p>
				</div>
			)}
		</div>
	);
});
