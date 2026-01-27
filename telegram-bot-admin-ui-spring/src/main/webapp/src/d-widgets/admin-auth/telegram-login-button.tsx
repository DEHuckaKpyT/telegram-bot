import { useEffect, useRef } from 'react';
import { adminSessionStore } from "../../g-shared/store/admin-session-store.ts";
import { authStorage } from "../../g-shared/storage/auth-storage.ts";
import type { TelegramAuthPayloadDto } from "../../g-shared/api/auth/dto/telegram-auth-payload.ts";
import { login } from "../../g-shared/api/auth/admin-auth-api.ts";
import { useNavigate } from 'react-router-dom';
import { Box } from "@mui/material";

type TelegramLoginButtonProps = {
    telegramBotName: string;
};

export function TelegramLoginButton({ telegramBotName }: TelegramLoginButtonProps) {
    const navigate = useNavigate();
    const containerRef = useRef<HTMLDivElement | null>(null);

    useEffect(() => {
        // @ts-ignore
        window.onTelegramAuth = async (user: TelegramAuthPayloadDto) => {
            try {
                const { token, telegramUserData } = await login(user);
                adminSessionStore.set({ accessToken: token });
                authStorage.set(telegramUserData);
                navigate('/', { replace: true });
            } catch (e) {
                console.error(e);
            }
        };

        const script = document.createElement('script');
        script.src = 'https://telegram.org/js/telegram-widget.js?22';
        script.async = true;
        script.setAttribute('data-telegram-login', telegramBotName);
        script.setAttribute('data-size', 'large');
        script.setAttribute('data-userpic', 'true');
        script.setAttribute('data-request-access', 'write');
        script.setAttribute('data-onauth', 'onTelegramAuth(user)');

        containerRef.current?.appendChild(script);

        return () => {
            if (containerRef.current) {
                containerRef.current.innerHTML = '';
            }
        };
    }, [ telegramBotName, navigate ]);

    return (
        <Box
            ref={containerRef}
            sx={{
                display: 'flex',
                justifyContent: 'center',
            }}
        />
    );
}
