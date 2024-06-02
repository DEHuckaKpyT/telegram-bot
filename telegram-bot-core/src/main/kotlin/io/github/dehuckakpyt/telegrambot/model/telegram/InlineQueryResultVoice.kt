package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * Represents a link to a voice recording in an .OGG container encoded with OPUS. By default, this
 * voice recording will be sent by the user. Alternatively, you can use *input_message_content* to send
 * a message with the specified content instead of the the voice message.
 *
 * @see [InlineQueryResultVoice] (https://core.telegram.org/bots/api/#inlinequeryresultvoice)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 bytes
 * @param voiceUrl A valid URL for the voice recording
 * @param title Recording title
 * @param caption *Optional*. Caption, 0-1024 characters after entities parsing
 * @param parseMode *Optional*. Mode for parsing entities in the voice message caption. See
 * [formatting options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param captionEntities *Optional*. List of special entities that appear in the caption, which can
 * be specified instead of *parse_mode*
 * @param voiceDuration *Optional*. Recording duration in seconds
 * @param replyMarkup *Optional*. [Inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) attached to the message
 * @param inputMessageContent *Optional*. Content of the message to be sent instead of the voice
 * recording
 */
public data class InlineQueryResultVoice(
    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
    /**
     * A valid URL for the voice recording
     */
    @get:JsonProperty("voice_url")
    @param:JsonProperty("voice_url")
    public val voiceUrl: String,
    /**
     * Recording title
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * *Optional*. Caption, 0-1024 characters after entities parsing
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: String? = null,
    /**
     * *Optional*. Mode for parsing entities in the voice message caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     */
    @get:JsonProperty("parse_mode")
    @param:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    /**
     * *Optional*. List of special entities that appear in the caption, which can be specified
     * instead of *parse_mode*
     */
    @get:JsonProperty("caption_entities")
    @param:JsonProperty("caption_entities")
    public val captionEntities: List<MessageEntity>? = null,
    /**
     * *Optional*. Recording duration in seconds
     */
    @get:JsonProperty("voice_duration")
    @param:JsonProperty("voice_duration")
    public val voiceDuration: Int? = null,
    /**
     * *Optional*. [Inline keyboard](https://core.telegram.org/bots/features#inline-keyboards)
     * attached to the message
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    override val replyMarkup: InlineKeyboardMarkup? = null,
    /**
     * *Optional*. Content of the message to be sent instead of the voice recording
     */
    @get:JsonProperty("input_message_content")
    @param:JsonProperty("input_message_content")
    public val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult {
    @get:JsonProperty("type")
    override val type: String = "voice"
}
