import axios from 'axios';
import qs from 'qs';
import { $adminSession } from "../store/adminSessionStore.ts";

export const client = axios.create({
    withCredentials: true,
});

const serializerParams: qs.IStringifyOptions = {
    skipNulls: true,
    arrayFormat: 'repeat',
};

client.defaults.headers.post['Content-Type'] = 'application/json';

client.interceptors.request.use(config => {
    config.paramsSerializer = {
        serialize: (params: any) => qs.stringify(params, serializerParams),
    };

    const session = $adminSession.get();
    if (session?.accessToken) {
        config.headers?.set?.('Authorization', `Bearer ${session.accessToken}`);
    }

    return config;
});

client.interceptors.response.use(
    response => response,
    error => {
        const status = error.response?.status;

        if (status === 401) {
            $adminSession.set(null);

            if (window.location.pathname !== "/admin-ui/login") {
                window.location.href = "/admin-ui/login";
            }
        }

        return Promise.reject(error);
    }
);
