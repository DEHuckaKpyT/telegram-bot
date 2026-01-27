import { BrowserRouter, Navigate, Outlet, Route, Routes } from 'react-router-dom';
import type { JSX } from "react";
import { LoginPage } from "../c-pages/auth/login/loginPage.tsx";
import { HomePage } from "../c-pages/home/homePage.tsx";
import { $adminSession } from "../g-shared/store/adminSessionStore.ts";
import { useStore } from '@nanostores/react';
import { AppLayout } from './layout/appLayout.tsx';
import { TelegramUsersPage } from '../c-pages/telegram-user/telegramUsersPage.tsx';
import { useAppConfig } from "./config/appConfigContext.tsx";

export function RequireAuth({ children }: { children: JSX.Element }) {
    const session = useStore($adminSession);

    if (!session) {
        return <Navigate to="/login" replace/>;
    }

    return children;
}

export function AppRouter() {
    const { adminPanelPrefix } = useAppConfig();

    return (
        <BrowserRouter basename={adminPanelPrefix}>
            <Routes>
                <Route path="/login" element={<LoginPage/>}/>

                <Route
                    element={
                        <RequireAuth>
                            <AppLayout>
                                <Outlet/>
                            </AppLayout>
                        </RequireAuth>
                    }
                >
                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/telegram-users" element={<TelegramUsersPage/>}/>
                </Route>

                <Route path="*" element={<Navigate to="/" replace/>}/>
            </Routes>
        </BrowserRouter>
    );
}
