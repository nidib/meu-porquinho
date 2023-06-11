import { Icon } from '@phosphor-icons/react';
import clsx from 'clsx';
import React from 'react';

interface Props extends Pick<React.HTMLProps<HTMLInputElement>, 'type' | 'placeholder'> {
	id: string;
	leftIcon?: Icon;
	placeholder: string;
}

export function Input(props: Props) {
	const { leftIcon: LeftIcon, ...rest } = props;

	return (
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
			<input className={clsx('flex-1', 'w-full', 'bg-transparent', 'text-lg', 'outline-none')} {...rest} />
		</div>
	);
}
