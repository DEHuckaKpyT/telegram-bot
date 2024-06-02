package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * Represents a link to an animated GIF file stored on the Telegram servers. By default, this
 * animated GIF file will be sent by the user with an optional caption. Alternatively, you can use
 * *input_message_content* to send a message with specified content instead of the animation.
 *
 * @see [InlineQueryResultCachedGif]
 * (https://core.telegram.org/bots/api/#inlinequeryresultcachedgif)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 bytes
 * @param gifFileId A valid file identifier for the GIF file
 * @param title *Optional*. Title for the result
 * @param caption *Optional*. Caption of the GIF file to be sent, 0-1024 characters after entities
 * parsing
 * @param parseMode *Optional*. Mode for parsing entities in the caption. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param captionEntities *Optional*. List of special entities that appear in the caption, which can
 * be specified instead of *parse_mode*
 * @param showCaptionAboveMedia *Optional*. Pass *True*, if the caption must be shown above the
 * message media
 * @param replyMarkup *Optional*. [Inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) attached to the message
 * @param inputMessageContent *Optional*. Content of the message to be sent instead of the GIF
 * animation
 */
public data class InlineQueryResultCachedGif(
    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
    /**
     * A valid file identifier for the GIF file
     */
    @get:JsonProperty("gif_file_id")
    @param:JsonProperty("gif_file_id")
    public val gifFileId: String,
    /**
     * *Optional*. Title for the result
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String? = null,
    /**
     * *Optional*. Caption of the GIF file to be sent, 0-1024 characters after entities parsing
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: String? = null,
    /**
     * *Optional*. Mode for parsing entities in the caption. See [formatting
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
     * *Optional*. Pass *True*, if the caption must be shown above the message media
     */
    @get:JsonProperty("show_caption_above_media")
    @param:JsonProperty("show_caption_above_media")
    public val showCaptionAboveMedia: Boolean? = null,
    /**
     * *Optional*. [Inline keyboard](https://core.telegram.org/bots/features#inline-keyboards)
     * attached to the message
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    override val replyMarkup: InlineKeyboardMarkup? = null,
    /**
     * *Optional*. Content of the message to be sent instead of the GIF animation
     */
    @get:JsonProperty("input_message_content")
    @param:JsonProperty("input_message_content")
    public val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult {
    @get:JsonProperty("type")
    override val type: String = "gif"
}
