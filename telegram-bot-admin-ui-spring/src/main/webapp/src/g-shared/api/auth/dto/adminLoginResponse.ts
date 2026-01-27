import type { UserData } from "../../../../f-entities/auth-session/userData.ts";

export type AdminLoginResponseDto = {
    token: string;
    telegramUserData: UserData;
}