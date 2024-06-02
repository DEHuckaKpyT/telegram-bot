package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * This object contains information about a chat that was shared with the bot using a
 * [KeyboardButtonRequestChat](https://core.telegram.org/bots/api/#keyboardbuttonrequestchat) button.
 *
 * @see [ChatShared] (https://core.telegram.org/bots/api/#chatshared)
 *
 * @author KScript
 *
 * @param requestId Identifier of the request
 * @param chatId Identifier of the shared chat. This number may have more than 32 significant bits
 * and some programming languages may have difficulty/silent defects in interpreting it. But it has at
 * most 52 significant bits, so a 64-bit integer or double-precision float type are safe for storing
 * this identifier. The bot may not have access to the chat and could be unable to use this identifier,
 * unless the chat is already known to the bot by some other means.
 * @param title *Optional*. Title of the chat, if the title was requested by the bot.
 * @param username *Optional*. Username of the chat, if the username was requested by the bot and
 * available.
 * @param photo *Optional*. Available sizes of the chat photo, if the photo was requested by the bot
 */
public data class ChatShared(
    /**
     * Identifier of the request
     */
    @get:JsonProperty("request_id")
    @param:JsonProperty("request_id")
    public val requestId: Long,
    /**
     * Identifier of the shared chat. This number may have more than 32 significant bits and some
     * programming languages may have difficulty/silent defects in interpreting it. But it has at most
     * 52 significant bits, so a 64-bit integer or double-precision float type are safe for storing
     * this identifier. The bot may not have access to the chat and could be unable to use this
     * identifier, unless the chat is already known to the bot by some other means.
     */
    @get:JsonProperty("chat_id")
    @param:JsonProperty("chat_id")
    public val chatId: Long,
    /**
     * *Optional*. Title of the chat, if the title was requested by the bot.
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String? = null,
    /**
     * *Optional*. Username of the chat, if the username was requested by the bot and available.
     */
    @get:JsonProperty("username")
    @param:JsonProperty("username")
    public val username: String? = null,
    /**
     * *Optional*. Available sizes of the chat photo, if the photo was requested by the bot
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: List<PhotoSize>? = null,
)
