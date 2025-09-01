package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Describes reply parameters for the message that is being sent.
 *
 * @see [ReplyParameters] (https://core.telegram.org/bots/api/#replyparameters)
 *
 * @author KScript
 *
 * @param messageId Identifier of the message that will be replied to in the current chat, or in the
 * chat *chat_id* if it is specified
 * @param chatId *Optional*. If the message to be replied to is from a different chat, unique
 * identifier for the chat or username of the channel (in the format `@channelusername`). Not supported
 * for messages sent on behalf of a business account and messages from channel direct messages chats.
 * @param allowSendingWithoutReply *Optional*. Pass *True* if the message should be sent even if the
 * specified message to be replied to is not found. Always *False* for replies in another chat or forum
 * topic. Always *True* for messages sent on behalf of a business account.
 * @param quote *Optional*. Quoted part of the message to be replied to; 0-1024 characters after
 * entities parsing. The quote must be an exact substring of the message to be replied to, including
 * *bold*, *italic*, *underline*, *strikethrough*, *spoiler*, and *custom_emoji* entities. The message
 * will fail to send if the quote isn't found in the original message.
 * @param quoteParseMode *Optional*. Mode for parsing entities in the quote. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param quoteEntities *Optional*. A JSON-serialized list of special entities that appear in the
 * quote. It can be specified instead of *quote_parse_mode*.
 * @param quotePosition *Optional*. Position of the quote in the original message in UTF-16 code
 * units
 * @param checklistTaskId *Optional*. Identifier of the specific checklist task to be replied to
 */
public data class ReplyParameters(
    /**
     * Identifier of the message that will be replied to in the current chat, or in the chat
     * *chat_id* if it is specified
     */
    @get:JsonProperty("message_id")
    @param:JsonProperty("message_id")
    public val messageId: Long,
    /**
     * *Optional*. If the message to be replied to is from a different chat, unique identifier for
     * the chat or username of the channel (in the format `@channelusername`). Not supported for
     * messages sent on behalf of a business account and messages from channel direct messages chats.
     */
    @get:JsonProperty("chat_id")
    @param:JsonProperty("chat_id")
    public val chatId: String? = null,
    /**
     * *Optional*. Pass *True* if the message should be sent even if the specified message to be
     * replied to is not found. Always *False* for replies in another chat or forum topic. Always
     * *True* for messages sent on behalf of a business account.
     */
    @get:JsonProperty("allow_sending_without_reply")
    @param:JsonProperty("allow_sending_without_reply")
    public val allowSendingWithoutReply: Boolean? = null,
    /**
     * *Optional*. Quoted part of the message to be replied to; 0-1024 characters after entities
     * parsing. The quote must be an exact substring of the message to be replied to, including *bold*,
     * *italic*, *underline*, *strikethrough*, *spoiler*, and *custom_emoji* entities. The message will
     * fail to send if the quote isn't found in the original message.
     */
    @get:JsonProperty("quote")
    @param:JsonProperty("quote")
    public val quote: String? = null,
    /**
     * *Optional*. Mode for parsing entities in the quote. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     */
    @get:JsonProperty("quote_parse_mode")
    @param:JsonProperty("quote_parse_mode")
    public val quoteParseMode: String? = null,
    /**
     * *Optional*. A JSON-serialized list of special entities that appear in the quote. It can be
     * specified instead of *quote_parse_mode*.
     */
    @get:JsonProperty("quote_entities")
    @param:JsonProperty("quote_entities")
    public val quoteEntities: List<MessageEntity>? = null,
    /**
     * *Optional*. Position of the quote in the original message in UTF-16 code units
     */
    @get:JsonProperty("quote_position")
    @param:JsonProperty("quote_position")
    public val quotePosition: Int? = null,
    /**
     * *Optional*. Identifier of the specific checklist task to be replied to
     */
    @get:JsonProperty("checklist_task_id")
    @param:JsonProperty("checklist_task_id")
    public val checklistTaskId: Long? = null,
) {
    public constructor(
        messageId: Long,
        chatId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        quote: String? = null,
        quoteParseMode: String? = null,
        quoteEntities: List<MessageEntity>? = null,
        quotePosition: Int? = null,
        checklistTaskId: Long? = null,
    ) : this(messageId, chatId.toString(), allowSendingWithoutReply, quote, quoteParseMode,
            quoteEntities, quotePosition, checklistTaskId)
}
