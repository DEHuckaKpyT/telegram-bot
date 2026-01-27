import { createContext, useContext } from 'react';
import type { AppConfig } from "../../f-entities/general/config.ts";

const AppConfigContext = createContext<AppConfig | null>(null);

export function useAppConfig(): AppConfig {
    const config = useContext(AppConfigContext);
    if (!config) {
        throw new Error('AppConfigProvider is missing');
    }
    return config;
}

export default AppConfigContext;
