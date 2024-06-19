package io.github.dehuckakpyt.telegrambot.model.telegram

import kotlin.String

/**
 * This object represents the scope to which bot commands are applied. Currently, the following 7
 * scopes are supported:
 *
 * * [BotCommandScopeDefault](https://core.telegram.org/bots/api/#botcommandscopedefault)
 * *
 * [BotCommandScopeAllPrivateChats](https://core.telegram.org/bots/api/#botcommandscopeallprivatechats)
 * *
 * [BotCommandScopeAllGroupChats](https://core.telegram.org/bots/api/#botcommandscopeallgroupchats)
 * *
 * [BotCommandScopeAllChatAdministrators](https://core.telegram.org/bots/api/#botcommandscopeallchatadministrators)
 * * [BotCommandScopeChat](https://core.telegram.org/bots/api/#botcommandscopechat)
 * *
 * [BotCommandScopeChatAdministrators](https://core.telegram.org/bots/api/#botcommandscopechatadministrators)
 * * [BotCommandScopeChatMember](https://core.telegram.org/bots/api/#botcommandscopechatmember)
 *
 * @see [BotCommandScope] (https://core.telegram.org/bots/api/#botcommandscope)
 *
 * @author KScript
 */
public sealed interface BotCommandScope {
    public val type: String
}
