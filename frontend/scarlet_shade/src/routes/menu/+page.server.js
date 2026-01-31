import { error, fail, redirect } from '@sveltejs/kit';
import { api } from '$script/server/apiClient.js';
import { setCookies } from '$script/server/apiCookies.js';
import { ENDPOINTS } from '$script/server/endpoints.js';
import { errorHandler } from '$script/server/errorHandler.js';

export const actions = {

    logout: async ({ fetch, cookies }) => {

        try {
            await api.post(fetch, ENDPOINTS.auth.logout, null, cookies);

            setCookies.clear(cookies);

            throw redirect(303, '/');
        }
        catch (e) {
            if (e.status === 303) {
				throw e;
			} 
            else if (e.status === 403) {
                throw redirect(303, '/');    
            }
            errorHandler(e, cookies);
        }
    },

    deleteUser: async ({ fetch, cookies }) => {

        try {
            await api.delete(fetch, ENDPOINTS.user.delete, cookies);

            setCookies.clear(cookies);

            throw redirect(303, '/');
        }
        catch (e) {
            if (e.status === 303) {
				throw e;
			} 
            else if (e.status === 403) {
                throw redirect(303, '/');    
            }
            errorHandler(e, cookies);
        }
    },

    volume: async ({ request, fetch, cookies }) => {

        const formData = await request.formData();

        const soundtrack = Number(formData.get('soundtrack'));
        const soundEffect = Number(formData.get('sound_effect'));

        try {
            await api.patch(
                fetch,
                ENDPOINTS.user.volume,
                {soundtrack, soundEffect},
                cookies
            );

            const rawUser = cookies.get("user");
            let user = JSON.parse(rawUser);

            user.soundtrack = soundtrack;
            user.soundEffect = soundEffect;

            setCookies.user(cookies, user);
        }
        catch (e) {
            if (e.status === 303) {
				throw e;
			} 
            else if (e.status === 403) {
                throw redirect(303, '/');    
            }
            errorHandler(e, cookies);
        }
    },

    control: async ({ request, fetch, cookies }) => {

        const formData = await request.formData();

        const keyboard = JSON.parse(formData.get('keyboard'));
        const gamepad = JSON.parse(formData.get('gamepad'));

        try {
            await api.patch(
                fetch,
                ENDPOINTS.control.update,
                {keyboard, gamepad},
                cookies
            );
        }
        catch (e) {
            if (e.status === 303) {
				throw e;
			} 
            else if (e.status === 403) {
                throw redirect(303, '/');    
            }
            errorHandler(e, cookies);
        }
    },

    configuration: async ({ request, fetch, cookies }) => {

        const formData = await request.formData();

        const username = formData.get("username");
        const email = formData.get("email");
        const password = formData.get("password");

        try {
            const {data, res} = await api.put(
                fetch,
                ENDPOINTS.user.update,
                {username, email, password},
                cookies
            );

            setCookies.token(cookies, res);
        }
        catch (e) {
            if (e.status === 303) {
				throw e;
			} 
            else if (e.status === 403) {
                throw redirect(303, '/');    
            }
            else if (e.status === 400 || e.status === 409) {
                // I will do the fail later...
            }
            errorHandler(e, cookies);
        }
    },

    createSlot: async ({ request, fetch, cookies }) => {

        const formData = await request.formData();

        const numberSlot = formData.get("numberSlot");

        try {
            const {data, res} = await api.post(
                fetch,
                ENDPOINTS.slot.create + numberSlot,
                null,
                cookies
            );

            const user = JSON.parse(cookies.get("user"));

            switch (Number(numberSlot)) {
                case 1:
                    user.slotOne = { numberSlot: data.numberSlot, gameCompleted: data.gameCompleted };
                    break;
                case 2:
                    user.slotTwo = { numberSlot: data.numberSlot, gameCompleted: data.gameCompleted };
                    break;
                case 3:
                    user.slotThree = { numberSlot: data.numberSlot, gameCompleted: data.gameCompleted };
                    break;
                case 4:
                    user.slotFour = { numberSlot: data.numberSlot, gameCompleted: data.gameCompleted };
                    break;
            }

            setCookies.user(cookies, user);
            setCookies.game(cookies, data);

            throw redirect(303, '/menu/game');
        }
        catch (e) {
            if (e.status === 303) {
				throw e;
			} 
            else if (e.status === 403) {
                throw redirect(303, '/');    
            }
            errorHandler(e, cookies);
        }
    },

    getSlot: async ({ request, fetch, cookies }) => {

        const formData = await request.formData();
        
        const numberSlot = formData.get("numberSlot");

        try {

            const {data, res} = await api.get(
                fetch,
                ENDPOINTS.slot.get + numberSlot,
                cookies
            );

            setCookies.game(cookies, data);

            throw redirect(303, '/menu/game');
        }
        catch (e) {

            if (e.status === 303) {
				throw e;
			} 
            else if (e.status === 403) {
                throw redirect(303, '/');    
            }
            errorHandler(e, cookies);
        }
    },

    deleteSlot: async ({ request, fetch, cookies }) => {
        
        const formData = await request.formData();

        const numberSlot = formData.get("numberSlot");

        try {
            const {data, res} = await api.delete(
                fetch,
                ENDPOINTS.slot.delete + numberSlot,
                cookies
            );

            const user = JSON.parse(cookies.get("user"));
                
            switch (Number(numberSlot)) {
                case 1:
                    user.slotOne = null;
                    break;
                case 2:
                    user.slotTwo = null;
                    break;
                case 3:
                    user.slotThree = null;
                    break;
                case 4:
                    user.slotFour = null;
                    break;
            }

            setCookies.user(cookies, user);
            cookies.delete("game", {path: '/'});
        }
        catch (e) {
            if (e.status === 303) {
				throw e;
			} 
            else if (e.status === 403) {
                throw redirect(303, '/');    
            }
            errorHandler(e, cookies);
        }
    }
}

export async function load({ fetch, cookies }) {

    try {
        const {data: controlData, res: controlRes} = await api.get(fetch, ENDPOINTS.control.get, cookies);
        const {data: userData, res: userRes} = await api.get(fetch, ENDPOINTS.user.get, cookies);

        const user = JSON.parse(cookies.get('user'));

        return {user, controlData, userData};
    }
    catch (e) {
        if (e.status === 303) {
			throw e;
		} 
        else if (e.status === 403) {
            throw redirect(303, '/');    
        }
        errorHandler(e, cookies);
    }
}