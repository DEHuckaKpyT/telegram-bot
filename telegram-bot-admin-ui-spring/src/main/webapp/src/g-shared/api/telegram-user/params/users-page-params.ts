import type { BasePageParams } from "../../../params/page-params.ts";

export type UsersPageParams = {
    anyStringFieldContainsIgnoreCase?: string;
    userIdsIn?: string[];
    usernameContainsIgnoreCase?: string;
} & BasePageParams;