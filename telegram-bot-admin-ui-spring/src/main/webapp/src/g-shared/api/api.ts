import type { AppConfig } from "../../f-entities/general/config.ts";
import { telegramUsersApi } from "./telegram-user/telegramUserApi.ts";
import { adminAuthApi } from "./auth/adminAuthApi.ts";


export function createApi(config: AppConfig) {
    // const client = createHttpClient(config.apiPrefix);

    return {
        adminAuth: adminAuthApi(config),
        telegramUsers: telegramUsersApi(config),
    };
}

export type Api = ReturnType<typeof createApi>;