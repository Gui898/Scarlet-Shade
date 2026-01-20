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
    },

    volume: async({ request, fetch, cookies }) => {
        const res = await fetch('http://localhost:8080/user/volume', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
        });

        if(!res.ok){
            return fail(401, {error: 'Fail Volume Set'});
        }

        const data = await res.json().catch(() => null);

        cookies.set("user", JSON.stringify(data), {
            path: '/',
            httpOnly: true,
            sameSite: 'lax',
        });

        throw redirect(303, '/menu');
    },

    control: async({ request, fetch, cookies }) => {
        const res = await fetch('http://localhost:8080/auth/?', {

            method: 'POST'
        });

        if(!res.ok){
            return fail(401, {error: 'Fail Volume Set'});
        }

        const data = await res.json().catch(() => null);

        // cookies.set("user", JSON.stringify(data), {
        //     path: '/',
        //     httpOnly: true,
        //     sameSite: 'lax',
        // });

        throw redirect(303, '/menu');
    },

    configuration: async({ request, fetch, cookies }) => {
        const res = await fetch('http://localhost:8080/auth/?', {

            method: 'POST'
        });

        if(!res.ok){
            return fail(401, {error: 'Fail Volume Set'});
        }

        const data = await res.json().catch(() => null);

        // cookies.set("user", JSON.stringify(data), {
        //     path: '/',
        //     httpOnly: true,
        //     sameSite: 'lax',
        // });

        throw redirect(303, '/menu');
    },


}

export async function load({ cookies }) {
    const raw = cookies.get('user');

    if (!raw) {
        return { data: null };
    }

    const data = JSON.parse(raw);

    return data;
}