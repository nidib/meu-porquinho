import { useEffect, useState } from 'react';
import { Navigate, RouterProvider, createBrowserRouter } from 'react-router-dom';

import { HomePage } from 'src/pages/home/home-page';
import { SignUpPage } from 'src/pages/sign-up/sign-up-page';
import { DashboardPage } from 'src/pages/dashboard/dashboard-page';
import { BankAccountsPage } from 'src/pages/bank-accounts/bank-accounts-page';
import { CreateBankAccountPage } from 'src/pages/create-bank-account/create-bank-account-page';

function Protected(props: { children: JSX.Element }) {
	const { children } = props;
	const [token, setToken] = useState<string | null>(null);
	const [hasLoaded, setHasLoaded] = useState(false);

	useEffect(() => {
		setToken(localStorage.getItem('token'));
		setHasLoaded(true);
	}, []);

	if (!hasLoaded) {
		return <p>Autenticando...</p>;
	}

	if (!token) {
		return <Navigate to={'/'} />;
	}

	return children;
}

const router = createBrowserRouter([
	{
		path: '/',
		element: <HomePage />,
	},
	{
		path: '/criar-conta',
		element: <SignUpPage />,
	},
	{
		path: '/home',
		element: (
			<Protected>
				<DashboardPage />
			</Protected>
		),
	},
	{
		path: '/contas',
		element: (
			<Protected>
				<BankAccountsPage />
			</Protected>
		),
	},
	{
		path: '/criar-conta-bancaria',
		element: (
			<Protected>
				<CreateBankAccountPage />
			</Protected>
		),
	},
]);

export function Router() {
	return <RouterProvider router={router} />;
}
