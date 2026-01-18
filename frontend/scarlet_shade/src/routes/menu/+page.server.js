import { fail, redirect } from '@sveltejs/kit';

export const actions = {

    logout: async ({ request, fetch, cookies }) => {

        const res = await fetch('http://localhost:8080/auth/logout', {

            method: 'POST'
        }) 

		if (!res.ok) {
			return fail(401, {error: 'Fail Logout'});
		}

        cookies.delete('access_token', {
            path: '/'
        });

        throw redirect(303, '/');
    }
}