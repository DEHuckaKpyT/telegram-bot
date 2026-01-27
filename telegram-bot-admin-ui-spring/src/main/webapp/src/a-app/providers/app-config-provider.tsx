import { useEffect, useState } from 'react';
import { appConfigStore } from "../../g-shared/store/app-config-store.ts";

export function AppConfigProvider({ children }: { children: React.ReactNode }) {
    const [ready, setReady] = useState(false);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        appConfigStore
            .load()
            .then(() => setReady(true))
            .catch(e => setError(e.message));
    }, []);

    if (error) {
        return <div>Config load error: {error}</div>;
    }

    if (!ready) {
        return <div>Loading…</div>;
    }

    return <>{children}</>;
}
