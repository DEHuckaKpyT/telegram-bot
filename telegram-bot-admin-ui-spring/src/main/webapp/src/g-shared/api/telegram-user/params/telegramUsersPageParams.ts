import type { BasePageParams } from "../../../params/pageParams.ts";

export type TelegramUsersPageParams = {
    anyStringFieldContainsIgnoreCase?: string;
    userIdsIn?: string[];
    usernameContainsIgnoreCase?: string;
} & BasePageParams;