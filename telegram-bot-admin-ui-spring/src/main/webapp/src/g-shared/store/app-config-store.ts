import type { AppConfig } from "../../f-entities/general/config.ts";


let config: AppConfig | null = null;

export const appConfigStore = {
    async load(): Promise<void> {
        const res = await fetch('/admin-ui/config');
        if (!res.ok) {
            throw new Error('Failed to load app config');
        }
        config = await res.json();
    },

    get(): AppConfig {
        if (!config) {
            throw new Error('AppConfig is not loaded');
        }
        return config;
    },
};
