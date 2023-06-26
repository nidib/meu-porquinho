/** @type {import('tailwindcss').Config} */
export default {
	content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
	theme: {
		extend: {
			colors: {
				'rosa-porquinho-100': '#FFADE3',
				'rosa-porquinho-200': '#EA78C4',
				'ceu-noite-100': '#121F25',
				'ceu-noite-200': '#051219',
			},
			backgroundImage: {
				stars: "url('/public/stars.png')",
			},
		},
	},
	plugins: [],
};
