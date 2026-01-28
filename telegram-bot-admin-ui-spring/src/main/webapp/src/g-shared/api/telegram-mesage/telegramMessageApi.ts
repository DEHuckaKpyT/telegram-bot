import { client } from '../client.ts';
import type { AxiosResponse } from 'axios';
import type { PageDto } from "../../dto/pageDto.ts";
import type { TelegramMessageDto } from "./dto/telegramMessageDto.ts";
import type { TelegramMessagesPageParams } from "./params/telegramMessagesPageParams.ts";
import type { TelegramMessageListDto } from "./dto/telegramMessageListDto.ts";
import type { AppConfig } from "../../../f-entities/general/config.ts";


export function telegramMessagesApi(config: AppConfig) {
    const baseUrl = `${config.apiPrefix}/admin/telegram-messages`;

    return {
        get(id: string) {
            return client.get<TelegramMessageDto>(
                `${baseUrl}/${id}`,
            ).then((response: AxiosResponse<TelegramMessageDto>) => response.data);
        },
        page(params: TelegramMessagesPageParams) {
            return client.get<PageDto<TelegramMessageListDto>>(
                `${baseUrl}/page`,
                { params },
            ).then((response: AxiosResponse<PageDto<TelegramMessageListDto>>) => response.data);
        },
    };
}