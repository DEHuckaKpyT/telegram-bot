import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { AppConfigProvider } from "./a-app/provider/appConfigProvider.tsx";
import { AppRouter } from "./a-app/router.tsx";
import { ApiProvider } from "./a-app/provider/apiProvider.tsx";

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <AppConfigProvider>
            <ApiProvider>
                <AppRouter/>
            </ApiProvider>
        </AppConfigProvider>
    </StrictMode>,
)
