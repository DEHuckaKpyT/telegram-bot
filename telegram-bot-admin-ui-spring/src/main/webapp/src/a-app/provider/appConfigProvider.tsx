import { useEffect, useState } from 'react';
import type { AppConfig } from "../../f-entities/general/config.ts";
import { getConfig } from "../../g-shared/api/admin-panel/adminPanelApi.ts";
import AppConfigContext from '../config/appConfigContext.tsx';

export function AppConfigProvider({ children }: { children: React.ReactNode }) {
    const [ config, setConfig ] = useState<AppConfig | null>(null);
    const [ error, setError ] = useState<string | null>(null);

    useEffect(() => {
        getConfig()
            .then(setConfig)
            .catch(e => setError(e.message));
    }, []);

    if (error) {
        return <div>Config load error: {error}</div>;
    }

    if (!config) {
        return <div>Loading…</div>;
    }

    return (
        <AppConfigContext.Provider value={config}>
            {children}
        </AppConfigContext.Provider>
    );
}