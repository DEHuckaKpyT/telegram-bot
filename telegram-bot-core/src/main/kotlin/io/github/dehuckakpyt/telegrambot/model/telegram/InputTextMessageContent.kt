package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * Represents the [content](https://core.telegram.org/bots/api/#inputmessagecontent) of a text
 * message to be sent as the result of an inline query.
 *
 * @see [InputTextMessageContent] (https://core.telegram.org/bots/api/#inputtextmessagecontent)
 *
 * @author KScript
 *
 * @param messageText Text of the message to be sent, 1-4096 characters
 * @param parseMode *Optional*. Mode for parsing entities in the message text. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param entities *Optional*. List of special entities that appear in message text, which can be
 * specified instead of *parse_mode*
 * @param linkPreviewOptions *Optional*. Link preview generation options for the message
 */
public data class InputTextMessageContent(
    /**
     * Text of the message to be sent, 1-4096 characters
     */
    @get:JsonProperty("message_text")
    @param:JsonProperty("message_text")
    public val messageText: String,
    /**
     * *Optional*. Mode for parsing entities in the message text. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     */
    @get:JsonProperty("parse_mode")
    @param:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    /**
     * *Optional*. List of special entities that appear in message text, which can be specified
     * instead of *parse_mode*
     */
    @get:JsonProperty("entities")
    @param:JsonProperty("entities")
    public val entities: List<MessageEntity>? = null,
    /**
     * *Optional*. Link preview generation options for the message
     */
    @get:JsonProperty("link_preview_options")
    @param:JsonProperty("link_preview_options")
    public val linkPreviewOptions: LinkPreviewOptions? = null,
) : InputMessageContent
