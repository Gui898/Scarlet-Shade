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

        const formData = await request.formData();

        const keyboard = JSON.parse(formData.get('keyboard'));
        const gamepad = JSON.parse(formData.get('gamepad'));

        const res = await fetch('http://localhost:8080/control/update', {

            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${cookies.get('access_token')}`
            },
            body: JSON.stringify({keyboard, gamepad}) 
        });
        
        if(!res.ok){
            return fail(401, {error: 'Fail Volume Set'});
        }

        throw redirect(303, '/menu');
    },

    configuration: async({request, fetch, cookies}) => {

        const formData = await request.formData();

        const username = formData.get("username");
        const email = formData.get("email");
        const password = formData.get("password");

        const token = cookies.get('access_token');

        const res = await fetch("http://localhost:8080/user/update", {

            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${cookies.get('access_token')}`
            },
            body: JSON.stringify({username, email, password})
        });

        if (!res.ok) {
            return fail(401, {error: 'Fail Volume Set'});
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
}

export async function load({ fetch, cookies }) {
    const raw = cookies.get('user');
    const token = cookies.get('access_token');

    if (!raw || !token) {
        return {
            data: null,
            controls: null 
        };
    }

    const userData = JSON.parse(raw);
    const resControl = await fetch("http://localhost:8080/control/buttons", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });

    const resUser = await fetch("http://localhost:8080/user/configurations", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });

    const controls = await resControl.json().catch(() => null);
    const configurations = await resUser.json().catch(() => null);

    return {
        userData,
        controls,
        configurations
    };
}