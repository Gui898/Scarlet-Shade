import { fail } from '@sveltejs/kit';
import { API_BASE } from './endpoints.js';

export const api = {

	get(fetch, endpoint, cookies) {
		return apiFetch(fetch, endpoint, {}, cookies);
	},

	post(fetch, endpoint, body, cookies) {

		if (body == null) {
			return apiFetch(fetch, endpoint, {method: 'POST',}, cookies);
		}
		return apiFetch(fetch, endpoint, {
			method: 'POST',
			body: JSON.stringify(body)
		}, cookies);
	},

	put(fetch, endpoint, body, cookies) {

		return apiFetch(fetch, endpoint, {
			method: 'PUT',
			body: JSON.stringify(body)
		}, cookies);
	},

    patch(fetch, endpoint, body, cookies) {
		return apiFetch(fetch, endpoint, {
			method: 'PATCH',
			body: JSON.stringify(body)
		}, cookies);
	},

	delete(fetch, endpoint, cookies) {
		return apiFetch(fetch, endpoint, {
			method: 'DELETE'
		}, cookies);
	}
};

async function apiFetch(fetch, endpoint, options = {}, cookies) {

	const token = cookies?.get('access_token');
    
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers 
    };

    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    const res = await fetch(`${API_BASE}${endpoint}`, {
        headers,
		...options
    });

	if (!res.ok) {
        await mapError(res); 
    }

	const text = await res.text();
	const data = text ? JSON.parse(text) : null;

	return { data, res};
}

async function mapError(res) {

	const messages = {
		400: 'Bad request',
		401: 'Unauthorized',
        403: 'Unauthorized',
        404: 'Not Found',
		409: 'Conflict',
		500: 'Server error'
	};

	const errorData = {
        status: res.status,
        error: messages[res.status] ?? 'Unexpected error'
    };
    
    throw errorData;
}

function forwardCookies(res, cookies) {

	const setCookies = res.headers.getSetCookie?.();

	if (!setCookies) return;

	for (const raw of setCookies) {

		const [pair] = raw.split(';');
		const [name, value] = pair.split('=');

		cookies.set(name, value, {
			path: '/',
			httpOnly: true,
			sameSite: 'lax'
		});
	}
}