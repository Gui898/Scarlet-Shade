import { redirect } from "@sveltejs/kit";
import { setCookies } from "./apiCookies.js";

export function errorHandler(e, cookies) {

    setCookies.clear(cookies);
    
    const status =
        typeof e?.status === 'number' && e.status >= 400 && e.status <= 599
        ? e.status : 500;

    const message = encodeURIComponent(e?.error || 'Unexpected Error');

    throw redirect(303, `/error?status=${status}&message=${message}`);
}