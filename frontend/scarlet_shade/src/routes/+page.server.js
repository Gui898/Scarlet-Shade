import { fail, redirect } from '@sveltejs/kit';

export const actions = {

	login: async ({ request, fetch, cookies }) => {

		const formData = await request.formData();

		const username = formData.get('username');
		const password = formData.get('password');

		const res = await fetch('http://localhost:8080/auth/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				username,
				password
			})
		});

		if (!res.ok) {
			return fail(401, {error: 'Invalid Login'});
		}

		const setCookie = res.headers.get('set-cookie');
		
		const cookiesHeader = res.headers.getSetCookie(); 
		const tokenValue = cookiesHeader
			.find(c => c.includes('access_token='))
			?.split('access_token=')[1]
			?.split(';')[0];

		if (tokenValue) {
            cookies.set('access_token', tokenValue, {
                path: '/',
                httpOnly: true,
                sameSite: 'lax',
                secure: false, 
                maxAge: 259200
            });
        }

		throw redirect(303, '/menu');
	},

	register: async ({ request, fetch }) => {

		const formData = await request.formData();

		const username = formData.get('username');
		const email = formData.get('email');
		const password = formData.get('password');

		const res = await fetch('http://localhost:8080/auth/register', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				username,
				email,
				password
			})
		});

		if (!res.ok) {
			return fail(400, {error: 'Invalid Register'});
		}

		const setCookie = res.headers.get('set-cookie');

		const cookiesHeader = res.headers.getSetCookie();
		const tokenValue = cookiesHeader
			.find(c => c.includes('access_token='))
			?.split('access_token=')[1]
			?.split(';')[0];


		if (tokenValue) {
            cookies.set('access_token', tokenValue, {
                path: '/',
                httpOnly: true,
                sameSite: 'lax',
                secure: false, 
                maxAge: 259200
            });
        }

		throw redirect(303, '/menu');
	}
};