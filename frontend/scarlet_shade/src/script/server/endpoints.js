export const API_BASE = "http://localhost:8080/";

export const ENDPOINTS = {

    auth: { 
        login: "auth/login",
        register: "auth/register",
        logout: "auth/logout"
    },

    user: {
        get: "user/configurations",
        update: "user/update",
        delete: "user/delete",
        volume: "user/volume"
    },

    control: {
        get: "control/buttons",
        update: "control/update"
    },

    slot: {
        create: "slot/create?number=",
        delete: "slot/delete?number=",
        get: "slot/get?number="
    }
}