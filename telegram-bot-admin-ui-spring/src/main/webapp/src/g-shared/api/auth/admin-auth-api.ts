import { client } from '../client.ts';
import type { AxiosResponse } from 'axios';
import type { TelegramAuthPayloadDto } from "./dto/telegram-auth-payload.ts";
import type { AdminLoginResponseDto } from "./dto/admin-login-response.ts";

const baseUrl = `/admin/auth`;
// const baseUrl = `${appConfig.host}/admin/telegram-users`;


export const login = (payload: TelegramAuthPayloadDto): Promise<AdminLoginResponseDto> =>
    client.post<AdminLoginResponseDto>(
        `${baseUrl}/login`,
        payload,
    ).then((response: AxiosResponse<AdminLoginResponseDto>) => response.data);

export const logout = (): Promise<void> =>
    client.post(
        `${baseUrl}/logout`,
    );
