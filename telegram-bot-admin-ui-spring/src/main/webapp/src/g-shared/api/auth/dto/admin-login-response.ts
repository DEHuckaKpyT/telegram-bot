import type { UserData } from "../../../../f-entities/auth-session/user-data.ts";

export type AdminLoginResponseDto = {
    token: string;
    telegramUserData: UserData;
}