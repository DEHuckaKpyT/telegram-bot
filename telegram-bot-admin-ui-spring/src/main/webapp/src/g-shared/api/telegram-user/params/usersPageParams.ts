import type { BasePageParams } from "../../../params/pageParams.ts";

export type UsersPageParams = {
    anyStringFieldContainsIgnoreCase?: string;
    userIdsIn?: string[];
    usernameContainsIgnoreCase?: string;
} & BasePageParams;