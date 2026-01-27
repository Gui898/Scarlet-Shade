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

        if (res === 401) {
            return fail(401, { error: 'Unauthorized Logout' });
        }
        else if (res === 403) {
            return fail(403, { error: 'Unauthorized Logout' });
        }
        else if (res === 500) {
            return fail(500, { error: 'Server Error' });
        }
        else if (!res.ok) {
            return fail(500, { error: 'Invalid Logout' });
        }

        const ALL_COOKIES = ["access_token", "user", "game"];
        for (let i = 0; i < ALL_COOKIES.length; i++) {

            cookies.delete(ALL_COOKIES[i], {
                path: '/'
            });
        }

        throw redirect(303, '/');
    },

    volume: async ({ request, fetch, cookies }) => {

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

        if (res === 400) {
            return fail(400, { error: 'Bad Request' });
        }
        else if (res === 401) {
            return fail(401, { error: 'Unauthorized Token' });
        }
        else if (res === 403) {
            return fail(403, { error: 'Unauthorized Token' });
        }
        else if (res === 404) {
            return fail(404, { error: 'User Not Found' });
        }
        else if (res === 500) {
            return fail(500, { error: 'Server Error' });
        }
        else if (!res.ok) {
            return fail(500, { error: 'Cannot Update Volume' });
        }
    },

    control: async ({ request, fetch, cookies }) => {

        const formData = await request.formData();

        const keyboard = JSON.parse(formData.get('keyboard'));
        const gamepad = JSON.parse(formData.get('gamepad'));

        const res = await fetch('http://localhost:8080/control/update', {

            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${cookies.get('access_token')}`
            },
            body: JSON.stringify({ keyboard, gamepad })
        });

        if (res === 400) {
            return fail(400, { error: 'Bad Request' });
        }
        else if (res === 401) {
            return fail(401, { error: 'Unauthorized Token' });
        }
        else if (res === 403) {
            return fail(403, { error: 'Unauthorized Token' });
        }
        else if (res === 404) {
            return fail(404, { error: 'Control Not Found' });
        }
        else if (res === 500) {
            return fail(500, { error: 'Server Error' });
        }
        else if (!res.ok) {
            return fail(500, { error: 'Cannot Update Controls' });
        }
    },

    configuration: async ({ request, fetch, cookies }) => {

        const formData = await request.formData();

        const username = formData.get("username");
        const email = formData.get("email");
        const password = formData.get("password");

        const token = cookies.get('access_token');

        const res = await fetch("http://localhost:8080/user/update", {

            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({ username, email, password })
        });

        if (res === 400) {
            return fail(400, { error: 'Bad Request' });
        }
        else if (res === 401) {
            return fail(401, { error: 'Unauthorized Token' });
        }
        else if (res === 403) {
            return fail(403, { error: 'Unauthorized Token' });
        }
        else if (res === 404) {
            return fail(404, { error: 'User Not Found' });
        }
        else if (res === 500) {
            return fail(500, { error: 'Server Error' });
        }
        else if (!res.ok) {
            return fail(500, { error: 'Cannot Edit This User' });
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
    },

    deleteUser: async ({ request, fetch, cookies }) => {
        const token = cookies.get("access_token");

        const res = await fetch("http://localhost:8080/user/delete", {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });

        if (res === 401) {
            return fail(401, { error: 'Unauthorized Delete User' });
        }
        else if (res === 403) {
            return fail(403, { error: 'Unauthorized Delete user' });
        }
        else if (res === 500) {
            return fail(500, { error: 'Server Error' });
        }
        else if (!res.ok) {
            return fail(500, { error: 'Invalid Logout' });
        }

        const ALL_COOKIES = ["access_token", "user", "game"];
        for (let i = 0; i < ALL_COOKIES.length; i++) {

            cookies.delete(ALL_COOKIES[i], {
                path: '/'
            });
        }

        throw redirect(303, '/');
    },

    createSlot: async ({ request, fetch, cookies }) => {

        const raw = JSON.parse(cookies.get("user"));
        const formData = await request.formData();
        const numberSlot = Number(formData.get("numberSlot"));

        const token = cookies.get("access_token");
        const res = await fetch(`http://localhost:8080/slot/create?number=${numberSlot}`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });

        if (res === 400) {
            return fail(400, { error: 'Bad Request' });
        }
        else if (res === 401) {
            return fail(401, { error: 'Unauthorized Token' });
        }
        else if (res === 403) {
            return fail(403, { error: 'Unauthorized Token' });
        }
        else if (res === 500) {
            return fail(500, { error: 'Server Error' });
        }
        else if (!res.ok) {
            return fail(500, { error: 'Cannot Create a Slot' });
        }

        const data = await res.json().catch(() => null);

        switch (numberSlot) {
            case 1:
                raw.slotOne = { numberSlot: data.numberSlot, gameCompleted: data.gameCompleted };
                break;
            case 2:
                raw.slotTwo = { numberSlot: data.numberSlot, gameCompleted: data.gameCompleted };
                break;
            case 3:
                raw.slotThree = { numberSlot: data.numberSlot, gameCompleted: data.gameCompleted };
                break;
            case 4:
                raw.slotFour = { numberSlot: data.numberSlot, gameCompleted: data.gameCompleted };
                break;
        }

        cookies.set("user", JSON.stringify(raw), {
            path: '/',
            httpOnly: true,
            sameSite: 'lax',
        });

        cookies.set("game", JSON.stringify(data), {
            path: '/',
            httpOnly: true,
            sameSite: 'lax',
        });

        throw redirect(303, '/menu/game');
    },

    getSlot: async ({ request, fetch, cookies }) => {

        const formData = await request.formData();
        const numberSlot = Number(formData.get("numberSlot"));

        const token = cookies.get("access_token");
        const res = await fetch(`http://localhost:8080/slot/get?number=${numberSlot}`, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });

        if (res === 404) {
            return fail(404, { error: 'Slot Not Found' });
        }
        else if (res === 500) {
            return fail(500, { error: 'Server Error' });
        }
        else if (!res.ok) {
            return fail(500, { error: 'Cannot Get Slot' });
        }

        const data = await res.json().catch(() => null);
        cookies.set("game", JSON.stringify(data), {
            path: '/',
            httpOnly: true,
            sameSite: 'lax',
        });

        throw redirect(303, '/menu/game');
    },

    deleteSlot: async ({ request, fetch, cookies }) => {
        const formData = await request.formData();
        const raw = JSON.parse(cookies.get("user"));

        const numberSlot = Number(formData.get("numberSlot"));
        const token = cookies.get("access_token");

        const res = await fetch(`http://localhost:8080/slot/delete?number=${numberSlot}`, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });

        if (res === 400) {
            return fail(400, { error: 'Bad Request' });
        }
        else if (res === 401) {
            return fail(401, { error: 'Unauthorized Token' });
        }
        else if (res === 403) {
            return fail(403, { error: 'Unauthorized Token' });
        }
        else if (res === 404) {
            return fail(404, { error: 'Slot Not Found' });
        }
        else if (res === 500) {
            return fail(500, { error: 'Server Error' });
        }
        else if (!res.ok) {
            return fail(500, { error: 'Cannot Delete' });
        }

        switch (numberSlot) {
            case 1:
                raw.slotOne = null;
                break;
            case 2:
                raw.slotTwo = null;
                break;
            case 3:
                raw.slotThree = null;
                break;
            case 4:
                raw.slotFour = null;
                break;
        }

        cookies.set("user", JSON.stringify(raw), {
            path: '/',
            httpOnly: true,
            sameSite: 'lax',
        });

        cookies.delete("game", {
            path: '/'
        });
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

    if (resControl === 400) {
        return fail(400, { error: 'Bad Controls Values' });
    }
    else if (resControl === 401 || resUser === 401) {
        return fail(401, { error: 'Unauthorized Token' });
    }
    else if (resControl === 403 || resUser === 403) {
        return fail(403, { error: 'Unauthorized Token' });
    }
    else if (resControl === 404) {
        return fail(404, { error: 'Controls Not Found' });
    }
    else if (resControl === 500 || resUser === 500) {
        return fail(500, { error: 'Server Error' });
    }
    else if (!resControl.ok || !resUser.ok) {
        return fail(500, { error: 'Invalid Menu Access' });
    }

    const controls = await resControl.json().catch(() => null);
    const configurations = await resUser.json().catch(() => null);

    return {
        userData,
        controls,
        configurations
    };
}