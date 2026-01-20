import { fail, redirect } from '@sveltejs/kit';

export const actions = {

    logout: async ({ request, fetch, cookies }) => {

        const token = cookies.get('access_token');

        const res = await fetch('http://localhost:8080/auth/logout', {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }) 

		if (!res.ok) {
			return fail(401, {error: 'Fail Logout'});
		}

        const ALL_COOKIES = ["access_token", "user"];
        for (let i = 0; i < ALL_COOKIES.length; i++) {
            
            cookies.delete(ALL_COOKIES[i], {
                path: '/'
            });
        }

        throw redirect(303, '/');
    },

    volume: async({ request, fetch, cookies }) => {

        const formData = await request.formData();

        const soundtrack = Number(formData.get('soundtrack'));
        const soundEffect = Number(formData.get('sound_effect'));

        const token = cookies.get('access_token');
        
        const res = await fetch('http://localhost:8080/user/volume', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` 
            },
            body: JSON.stringify({
                soundtrack,
                soundEffect
            })
        });

        const rawUser = cookies.get("user");
        let user = JSON.parse(rawUser);

        user.soundtrack = soundtrack;
        user.soundEffect = soundEffect;

        cookies.set('user', JSON.stringify(user), {
            path: '/',
            httpOnly: true,
            sameSite: 'lax'
        });

        if(!res.ok){
            return fail(401, {error: 'Fail Volume Set'});
        }
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