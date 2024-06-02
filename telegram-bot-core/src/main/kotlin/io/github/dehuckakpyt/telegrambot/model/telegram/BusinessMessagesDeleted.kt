package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * This object is received when messages are deleted from a connected business account.
 *
 * @see [BusinessMessagesDeleted] (https://core.telegram.org/bots/api/#businessmessagesdeleted)
 *
 * @author KScript
 *
 * @param businessConnectionId Unique identifier of the business connection
 * @param chat Information about a chat in the business account. The bot may not have access to the
 * chat or the corresponding user.
 * @param messageIds The list of identifiers of deleted messages in the chat of the business account
 */
public data class BusinessMessagesDeleted(
    /**
     * Unique identifier of the business connection
     */
    @get:JsonProperty("business_connection_id")
    @param:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    /**
     * Information about a chat in the business account. The bot may not have access to the chat or
     * the corresponding user.
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * The list of identifiers of deleted messages in the chat of the business account
     */
    @get:JsonProperty("message_ids")
    @param:JsonProperty("message_ids")
    public val messageIds: List<Long>,
)
