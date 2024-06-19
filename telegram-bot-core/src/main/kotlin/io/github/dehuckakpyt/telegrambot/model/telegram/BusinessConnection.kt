package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * Describes the connection of the bot with a business account.
 *
 * @see [BusinessConnection] (https://core.telegram.org/bots/api/#businessconnection)
 *
 * @author KScript
 *
 * @param id Unique identifier of the business connection
 * @param user Business account user that created the business connection
 * @param userChatId Identifier of a private chat with the user who created the business connection.
 * This number may have more than 32 significant bits and some programming languages may have
 * difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a 64-bit
 * integer or double-precision float type are safe for storing this identifier.
 * @param date Date the connection was established in Unix time
 * @param canReply True, if the bot can act on behalf of the business account in chats that were
 * active in the last 24 hours
 * @param isEnabled True, if the connection is active
 */
public data class BusinessConnection(
    /**
     * Unique identifier of the business connection
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: String,
    /**
     * Business account user that created the business connection
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    public val user: User,
    /**
     * Identifier of a private chat with the user who created the business connection. This number
     * may have more than 32 significant bits and some programming languages may have difficulty/silent
     * defects in interpreting it. But it has at most 52 significant bits, so a 64-bit integer or
     * double-precision float type are safe for storing this identifier.
     */
    @get:JsonProperty("user_chat_id")
    @param:JsonProperty("user_chat_id")
    public val userChatId: Long,
    /**
     * Date the connection was established in Unix time
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    public val date: Long,
    /**
     * True, if the bot can act on behalf of the business account in chats that were active in the
     * last 24 hours
     */
    @get:JsonProperty("can_reply")
    @param:JsonProperty("can_reply")
    public val canReply: Boolean,
    /**
     * True, if the connection is active
     */
    @get:JsonProperty("is_enabled")
    @param:JsonProperty("is_enabled")
    public val isEnabled: Boolean,
)
