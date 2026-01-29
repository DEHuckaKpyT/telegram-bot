import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { AppConfigProvider } from "./a-app/provider/appConfigProvider.tsx";
import { AppRouter } from "./a-app/router.tsx";
import { ApiProvider } from "./a-app/provider/apiProvider.tsx";
import { ThemeProvider } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import { darkTheme } from "./a-app/theme/theme.ts";

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <ThemeProvider theme={darkTheme}>
            <CssBaseline/>
            <AppConfigProvider>
                <ApiProvider>
                    <AppRouter/>
                </ApiProvider>
            </AppConfigProvider>
        </ThemeProvider>
    </StrictMode>,
)
