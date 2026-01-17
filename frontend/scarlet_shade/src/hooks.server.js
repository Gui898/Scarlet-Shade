import { redirect } from '@sveltejs/kit';

export async function handle({ event, resolve }) {
    const { pathname } = event.url;

    const token = event.cookies.get('access_token');

    if (pathname.startsWith('/menu') && !token) {
        throw redirect(303, '/');
    }

    return resolve(event);
}