import { fail, redirect } from '@sveltejs/kit';

export const actions = {

	login: async ({ request, fetch }) => {

		const formData = await request.formData();

		const username = formData.get('username');
		const password = formData.get('password');

		const res = await fetch('http://localhost:8080/auth/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			credentials: 'include',
			body: JSON.stringify({
				username,
				password
			})
		});

		if (!res.ok) {
			return fail(401, {error: 'Invalid Login'});
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
			credentials: 'include',
			body: JSON.stringify({
				username,
				email,
				password
			})
		});

		if (!res.ok) {
			return fail(400, {error: 'Invalid Register'});
		}

		throw redirect(303, '/menu');
	}
};