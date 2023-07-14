import { Icon } from '@phosphor-icons/react';
import { Category } from 'src/components/categories-slider/categories-slider-container';

type Props = {
	categories: (Category & { icon: Icon })[];
};

export function CategoriesSlider(props: Props) {
	const { categories } = props;

	return (
		<div className="flex gap-5 overflow-x-scroll">
			{categories.map(({ name, icon: TheIcon }, i) => (
				<div key={`${name}-${i}`} className="flex flex-col gap-1">
					<div className="flex h-[100px] w-[100px] flex-shrink-0 items-center justify-center rounded-full bg-rosa-porquinho-100 shadow-md">
						<TheIcon size={'50%'} className="text-ceu-noite-200" />
					</div>
					<p className="text-center lowercase text-gray-500">{name}</p>
				</div>
			))}
		</div>
	);
}
