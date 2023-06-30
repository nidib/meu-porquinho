import { RouterProvider, createBrowserRouter } from 'react-router-dom';

import { HomePage } from 'src/pages/home/home-page';
import { SignUpPage } from 'src/pages/home/sign-up-page';

const router = createBrowserRouter([
	{
		path: '/',
		element: <HomePage />,
	},
	{
		path: '/criar-conta',
		element: <SignUpPage />,
	},
]);

export function Router() {
	return <RouterProvider router={router} />;
}
