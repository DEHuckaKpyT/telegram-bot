import axios from 'axios';
import qs from 'qs';
import { $adminSession } from "../store/admin-session-store.ts";
import { appConfigStore } from "../store/app-config-store.ts";

export const client = axios.create({
    withCredentials: true,
});

const serializerParams: qs.IStringifyOptions = {
    skipNulls: true,
    arrayFormat: 'repeat',
};

client.defaults.headers.post['Content-Type'] = 'application/json';

client.interceptors.request.use(config => {
    const { host } = appConfigStore.get();
    config.baseURL = host;

    config.paramsSerializer = {
        serialize: (params: any) => qs.stringify(params, serializerParams),
    };

    const session = $adminSession.get();
    console.log("client: current session ", session);
    if (session?.accessToken) {
        config.headers?.set?.('Authorization', `Bearer ${session.accessToken}`);
    }

    return config;
});
