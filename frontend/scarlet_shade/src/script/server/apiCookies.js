const ACCESS_TOKEN_COOKIE = "access_token";
const USER_COOKIE = "user";
const GAME_COOKIE = "game";

export const setCookies = {

    token(cookies, response) {
		
        const setCookie = response.headers.get('set-cookie');
		
		const cookiesHeader = response.headers.getSetCookie(); 
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

    user(cookies, user) {
		cookies.set(USER_COOKIE, JSON.stringify(user), {
			path: '/',
			httpOnly: true,
			sameSite: 'lax'
		});
	},
 
    game(cookies, game) {
        cookies.set("game", JSON.stringify(game), {
            path: '/',
            httpOnly: true,
            sameSite: 'lax',
        });
    },

    clear(cookies) {
        cookies.delete(ACCESS_TOKEN_COOKIE, { path: '/' });
		cookies.delete(USER_COOKIE, { path: '/' });
        cookies.delete(GAME_COOKIE, { path: '/' });
    }
}