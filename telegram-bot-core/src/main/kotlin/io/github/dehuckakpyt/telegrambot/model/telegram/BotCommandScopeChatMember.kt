package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * Represents the [scope](https://core.telegram.org/bots/api/#botcommandscope) of bot commands,
 * covering a specific member of a group or supergroup chat.
 *
 * @see [BotCommandScopeChatMember] (https://core.telegram.org/bots/api/#botcommandscopechatmember)
 *
 * @author KScript
 *
 * @param chatId Unique identifier for the target chat or username of the target supergroup (in the
 * format `@supergroupusername`)
 * @param userId Unique identifier of the target user
 */
public data class BotCommandScopeChatMember(
    /**
     * Unique identifier for the target chat or username of the target supergroup (in the format
     * `@supergroupusername`)
     */
    @get:JsonProperty("chat_id")
    @param:JsonProperty("chat_id")
    public val chatId: String,
    /**
     * Unique identifier of the target user
     */
    @get:JsonProperty("user_id")
    @param:JsonProperty("user_id")
    public val userId: Long,
) : BotCommandScope {
    @get:JsonProperty("type")
    override val type: String = "chat_member"

    public constructor(chatId: Long, userId: Long) : this(chatId.toString(), userId)
}
