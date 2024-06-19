package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents the [scope](https://core.telegram.org/bots/api/#botcommandscope) of bot commands,
 * covering all group and supergroup chats.
 *
 * @see [BotCommandScopeAllGroupChats]
 * (https://core.telegram.org/bots/api/#botcommandscopeallgroupchats)
 *
 * @author KScript
 */
public class BotCommandScopeAllGroupChats() : BotCommandScope {
    @get:JsonProperty("type")
    override val type: String = "all_group_chats"
}
