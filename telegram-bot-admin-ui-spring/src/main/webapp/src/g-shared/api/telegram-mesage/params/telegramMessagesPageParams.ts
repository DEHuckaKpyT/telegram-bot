import type { BasePageParams } from "../../../params/pageParams.ts";

export type TelegramMessagesPageParams = {
    chatIdsIn?: string[];
    fromIdsIn?: string[];
} & BasePageParams;