import { createContext, type ReactNode, useContext, useMemo } from 'react';
import { useAppConfig } from "../config/appConfigContext.tsx";
import { type Api, createApi } from "../../g-shared/api/api.ts";

const ApiContext = createContext<Api | null>(null);

export function ApiProvider({ children }: { children: ReactNode }) {
    const config = useAppConfig();

    const api = useMemo(
        () => createApi(config),
        [ config ],
    );

    return (
        <ApiContext.Provider value={api}>
            {children}
        </ApiContext.Provider>
    );
}

export function useApi(): Api {
    const api = useContext(ApiContext);
    if (!api) {
        throw new Error('ApiProvider is missing');
    }
    return api;
}