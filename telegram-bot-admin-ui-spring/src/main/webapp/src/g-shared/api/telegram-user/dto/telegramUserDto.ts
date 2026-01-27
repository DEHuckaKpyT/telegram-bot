export interface TelegramUserDto {
    id: string;
    userId: number;
    username?: string;
    firstName: string;
    lastName?: string;
    languageCode?: string;
    available: boolean;
    updatedAt: Date;
    createdAt: Date;
}