import { useEffect, useRef } from 'react';
import { adminSessionStore } from "../../g-shared/store/adminSessionStore.ts";
import { authStorage } from "../../g-shared/storage/authStorage.ts";
import type { TelegramAuthPayloadDto } from "../../g-shared/api/auth/dto/telegramAuthPayload.ts";
import { useNavigate } from 'react-router-dom';
import { Box } from "@mui/material";
import { useApi } from "../../a-app/provider/apiProvider.tsx";
import { useAppConfig } from "../../a-app/config/appConfigContext.tsx";

type TelegramLoginButtonProps = {};

export function TelegramLoginButton({}: TelegramLoginButtonProps) {
    const { adminAuth } = useApi();
    const { telegramBotUsername } = useAppConfig();
    const navigate = useNavigate();
    const containerRef = useRef<HTMLDivElement | null>(null);

    useEffect(() => {
        // @ts-ignore
        window.onTelegramAuth = async (user: TelegramAuthPayloadDto) => {
            try {
                const { token, telegramUserData } = await adminAuth.login(user);
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
        script.setAttribute('data-telegram-login', telegramBotUsername);
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
    }, [ telegramBotUsername, navigate ]);

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
