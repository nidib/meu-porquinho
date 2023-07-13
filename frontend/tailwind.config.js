/** @type {import('tailwindcss').Config} */
export default {
	content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
	theme: {
		extend: {
			colors: {
				'rosa-porquinho-100': '#FFADE3',
				'rosa-porquinho-150': '#FF99DD',
				'rosa-porquinho-200': '#EA78C4',
				'rosa-porquinho-300': '#513B54',
				'ceu-noite-100': '#121F25',
				'ceu-noite-200': '#051219',
				'verde-100': '#47FF70',
				'vermelho-100': '#FF4747',
			},
			backgroundImage: {
				stars: "url('/public/stars.png')",
			},
		},
	},
	plugins: [],
};
