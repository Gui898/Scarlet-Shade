import { error, fail, redirect } from '@sveltejs/kit';
import { api } from '$script/server/apiClient.js';
import { setCookies } from '$script/server/apiCookies.js';
import { ENDPOINTS } from '$script/server/endpoints.js';
import { errorHandler } from '$script/server/errorHandler.js';

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
			else if (e.status === 401 || e.status === 403) {
				return fail(e.status, { 
					error: true,
					form: "login",
					message: "Incorrect username or password" });
			}
			errorHandler(e);
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
			else if (e.status === 409 || e.status === 400) {
				return fail(e.status, { 
					error: true,
					form: "register",
					message: "Username or email already exists" });
			}	
			errorHandler(e);
		}
	}
};