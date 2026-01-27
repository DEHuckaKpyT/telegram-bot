export type TelegramAuthPayloadDto = {
    id: number;
    username?: string;
    first_name: string;
    last_name?: string;
    auth_date: number;
    photo_url?: string;
    hash: string;
};