import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { AppConfigProvider } from "./a-app/providers/app-config-provider.tsx";
import { AppRouter } from "./a-app/router.tsx";

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <AppConfigProvider>
            <AppRouter/>
        </AppConfigProvider>
    </StrictMode>,
)
