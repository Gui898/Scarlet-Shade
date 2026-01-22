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

        const res = await fetch('http://localhost:8080/user/controls', {

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

    // configuration: async({ request, fetch, cookies }) => {
    //     const res = await fetch('http://localhost:8080/auth/?', {

    //         method: 'POST'
    //     });

    //     if(!res.ok){
    //         return fail(401, {error: 'Fail Volume Set'});
    //     }

    //     const data = await res.json().catch(() => null);

    //     cookies.set("user", JSON.stringify(data), {
    //         path: '/',
    //         httpOnly: true,
    //         sameSite: 'lax',
    //     });

    //     throw redirect(303, '/menu');
    // },


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
    const resControl = await fetch("http://localhost:8080/user/controls", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });

    const controls = await resControl.json().catch(() => null);

    return {
        userData,
        controls
    };
}