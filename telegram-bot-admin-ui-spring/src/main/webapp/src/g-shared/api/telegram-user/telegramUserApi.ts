import { client } from '../client.ts';
import type { AxiosResponse } from 'axios';
import type { PageDto } from "../../dto/pageDto.ts";
import type { TelegramUserDto } from "./dto/telegramUserDto.ts";
import type { UsersPageParams } from "./params/usersPageParams.ts";
import type { TelegramUserListDto } from "./dto/telegramUserListDto.ts";
import type { AppConfig } from "../../../f-entities/general/config.ts";


export function telegramUsersApi(config: AppConfig) {
    const baseUrl = `${config.apiPrefix}/admin/telegram-users`;

    return {
        get(id: string) {
            return client.get<TelegramUserDto>(
                `${baseUrl}/${id}`,
            ).then((response: AxiosResponse<TelegramUserDto>) => response.data);
        },
        page(params: UsersPageParams) {
            return client.get<PageDto<TelegramUserListDto>>(
                `${baseUrl}/page`,
                { params },
            ).then((response: AxiosResponse<PageDto<TelegramUserListDto>>) => response.data);
        },
    };
}