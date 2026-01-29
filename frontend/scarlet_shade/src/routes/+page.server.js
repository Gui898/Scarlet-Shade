import { fail, redirect } from '@sveltejs/kit';
import { api } from '$script/server/apiClient.js';
import { setCookies } from '$script/server/apiCookies.js';
import { ENDPOINTS } from '$script/server/endpoints.js';

export const actions = {

	login: async ({ request, fetch, cookies }) => {

		const formData = await request.formData();

		const username = formData.get('username');
		const password = formData.get('password');

		try {
			const {data, res} = await api.post(
				fetch, 
				ENDPOINTS.auth.login, 
				{username, password},
				cookies
			);

			setCookies.token(cookies, res);
			setCookies.user(cookies, data);

			throw redirect(303, '/menu');
		}
		catch(e) {

			if (e.status === 303) {
				throw e;
			} 

        	return fail(e.status || 500, { error: e.error || 'Connection Error' });
		}
	},

	register: async ({ request, fetch, cookies }) => {

		const formData = await request.formData();

		const username = formData.get('username');
		const email = formData.get('email');
		const password = formData.get('password');
		
		try {

			const {data, res} = await api.post(
				fetch,
				ENDPOINTS.auth.register,
				{username, email, password},
				cookies
			);

			setCookies.token(cookies, res);
			setCookies.user(cookies, data);

			throw redirect(303, '/menu');
		}
		catch(e) {

			if (e.status === 303) {
				throw e;
			} 

        	return fail(e.status || 500, { error: e.error || 'Connection Error' });
		}
	}
};