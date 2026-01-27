import { client } from '../client.ts';
import type { AxiosResponse } from 'axios';
import type { TelegramAuthPayloadDto } from "./dto/telegramAuthPayload.ts";
import type { AdminLoginResponseDto } from "./dto/adminLoginResponse.ts";
import type { AppConfig } from "../../../f-entities/general/config.ts";

export function adminAuthApi(config: AppConfig) {
    const baseUrl = `${config.apiPrefix}/admin/auth`;

    return {
        login(payload: TelegramAuthPayloadDto): Promise<AdminLoginResponseDto> {
            return client.post<AdminLoginResponseDto>(
                `${baseUrl}/login`,
                payload,
            ).then((response: AxiosResponse<AdminLoginResponseDto>) => response.data);
        },
        logout() {
            return client.post(
                `${baseUrl}/logout`,
            );
        },
    };
}