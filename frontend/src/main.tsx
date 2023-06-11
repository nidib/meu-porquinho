import React from 'react';
import ReactDOM from 'react-dom/client';

import { App } from 'src/app';

function main() {
	const root = document.getElementById('root');

	if (!root) {
		throw new Error('Elemento com id #root não encontrado');
	}

	ReactDOM.createRoot(root).render(
		<React.StrictMode>
			<App />
		</React.StrictMode>
	);
}

main();
