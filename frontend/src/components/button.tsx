import { cva, VariantProps } from 'class-variance-authority';
import { ReactNode } from 'react';

const button = cva(
	[
		'flex',
		'justify-center',
		'items-center',
		'rounded-2xl',
		'py-4',
		'px-8',
		'border-2',
		'text-lg',
		'transition-colors',
	],
	{
		variants: {
			intent: {
				primary: ['bg-rosa-porquinho-100', 'text-ceu-noite-200', 'border-transparent'],
				default: ['border-rosa-porquinho-100', 'text-rosa-porquinho-100'],
				ghost: ['border-none', 'text-gray-300', 'underline', 'py-1', 'hover:text-gray-50'],
			},
			disabled: {
				true: ['opacity-40', 'touch-none', 'cursor-default'],
			},
			fullWidth: {
				true: ['w-full'],
			},
		},
		compoundVariants: [
			{
				disabled: false,
				intent: 'primary',
				className: ['hover:bg-rosa-porquinho-200'],
			},
			{
				disabled: false,
				intent: 'default',
				className: [
					'hover:bg-rosa-porquinho-200',
					'hover:border-rosa-porquinho-200',
					'hover:text-ceu-noite-200',
				],
			},
		],
		defaultVariants: {
			intent: 'default',
			fullWidth: false,
			disabled: false,
		},
	}
);

interface Props extends VariantProps<typeof button> {
	children: ReactNode;
	onClick?: () => void;
	type?: 'button' | 'submit';
}

export function Button(props: Props) {
	const { type = 'button', onClick, children } = props;

	const handleClick = () => onClick && onClick();

	return (
		<button type={type} className={button(props)} onClick={handleClick}>
			{children}
		</button>
	);
}
