import type { AppConfig } from "../../f-entities/general/config.ts";
import { getConfig } from "../api/admin-panel/adminPanelApi.ts";


let config: AppConfig | null = null;

export const appConfigStore = {
    async load(): Promise<void> {
        config = await getConfig();
    },

    get(): AppConfig {
        if (!config) {
            throw new Error('AppConfig is not loaded');
        }
        return config;
    },
};
