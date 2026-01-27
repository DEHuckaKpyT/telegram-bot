import { client } from '../client.ts';
import type { AxiosResponse } from 'axios';
import type { PageDto } from "../../dto/page-dto.ts";
import type { TelegramUserDto } from "./dto/telegram-user-dto.ts";
import type { UsersPageParams } from "./params/users-page-params.ts";
import type { TelegramUserListDto } from "./dto/telegram-user-list-dto.ts";

const baseUrl = `/admin/telegram-users`;
// const baseUrl = `${appConfig.host}/admin/telegram-users`;


export const getTelegramUsersPage = (params: UsersPageParams): Promise<PageDto<TelegramUserListDto>> =>
    client.get<PageDto<TelegramUserListDto>>(
        `${baseUrl}/page`,
        { params },
    ).then((response: AxiosResponse<PageDto<TelegramUserListDto>>) => response.data);

export const getTelegramUser = (id: string): Promise<TelegramUserDto> =>
    client.get<TelegramUserDto>(
        `${baseUrl}/${id}`,
    ).then((response: AxiosResponse<TelegramUserDto>) => response.data);
