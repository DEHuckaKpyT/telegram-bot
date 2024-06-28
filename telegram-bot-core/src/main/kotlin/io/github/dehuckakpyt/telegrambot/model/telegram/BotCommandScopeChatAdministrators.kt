package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Represents the [scope](https://core.telegram.org/bots/api/#botcommandscope) of bot commands,
 * covering all administrators of a specific group or supergroup chat.
 *
 * @see [BotCommandScopeChatAdministrators]
 * (https://core.telegram.org/bots/api/#botcommandscopechatadministrators)
 *
 * @author KScript
 *
 * @param chatId Unique identifier for the target chat or username of the target supergroup (in the
 * format `@supergroupusername`)
 */
public data class BotCommandScopeChatAdministrators(
    /**
     * Unique identifier for the target chat or username of the target supergroup (in the format
     * `@supergroupusername`)
     */
    @get:JsonProperty("chat_id")
    @param:JsonProperty("chat_id")
    public val chatId: String,
) : BotCommandScope {
    @get:JsonProperty("type")
    override val type: String = "chat_administrators"

    public constructor(chatId: Long) : this(chatId.toString())
}
