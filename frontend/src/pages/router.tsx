import { RouterProvider, createBrowserRouter } from 'react-router-dom';

import { HomePage } from 'src/pages/home/home-page';

const router = createBrowserRouter([
	{
		path: '/',
		element: <HomePage />,
	},
]);

export function Router() {
	return <RouterProvider router={router} />;
}
