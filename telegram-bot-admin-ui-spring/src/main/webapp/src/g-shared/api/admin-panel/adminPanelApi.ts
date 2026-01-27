import { client } from '../client.ts';
import type { AxiosResponse } from 'axios';
import type { AppConfig } from "../../../f-entities/general/config.ts";

const baseUrl = `/admin-panel`;


export const getConfig = (): Promise<AppConfig> =>
    client.get<AppConfig>(
        `${baseUrl}/config`,
    ).then((response: AxiosResponse<AppConfig>) => response.data);
