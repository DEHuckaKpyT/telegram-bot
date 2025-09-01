package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Represents the [scope](https://core.telegram.org/bots/api/#botcommandscope) of bot commands,
 * covering a specific chat.
 *
 * @see [BotCommandScopeChat] (https://core.telegram.org/bots/api/#botcommandscopechat)
 *
 * @author KScript
 *
 * @param chatId Unique identifier for the target chat or username of the target supergroup (in the
 * format `@supergroupusername`). Channel direct messages chats and channel chats aren't supported.
 */
public data class BotCommandScopeChat(
    /**
     * Unique identifier for the target chat or username of the target supergroup (in the format
     * `@supergroupusername`). Channel direct messages chats and channel chats aren't supported.
     */
    @get:JsonProperty("chat_id")
    @param:JsonProperty("chat_id")
    public val chatId: String,
) : BotCommandScope {
    @get:JsonProperty("type")
    override val type: String = "chat"

    public constructor(chatId: Long) : this(chatId.toString())
}
