package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * Represents a link to an article or web page.
 *
 * @see [InlineQueryResultArticle] (https://core.telegram.org/bots/api/#inlinequeryresultarticle)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 Bytes
 * @param title Title of the result
 * @param inputMessageContent Content of the message to be sent
 * @param replyMarkup *Optional*. [Inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) attached to the message
 * @param url *Optional*. URL of the result
 * @param hideUrl *Optional*. Pass *True* if you don't want the URL to be shown in the message
 * @param description *Optional*. Short description of the result
 * @param thumbnailUrl *Optional*. Url of the thumbnail for the result
 * @param thumbnailWidth *Optional*. Thumbnail width
 * @param thumbnailHeight *Optional*. Thumbnail height
 */
public data class InlineQueryResultArticle(
    /**
     * Unique identifier for this result, 1-64 Bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
    /**
     * Title of the result
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * Content of the message to be sent
     */
    @get:JsonProperty("input_message_content")
    @param:JsonProperty("input_message_content")
    public val inputMessageContent: InputMessageContent,
    /**
     * *Optional*. [Inline keyboard](https://core.telegram.org/bots/features#inline-keyboards)
     * attached to the message
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    override val replyMarkup: InlineKeyboardMarkup? = null,
    /**
     * *Optional*. URL of the result
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String? = null,
    /**
     * *Optional*. Pass *True* if you don't want the URL to be shown in the message
     */
    @get:JsonProperty("hide_url")
    @param:JsonProperty("hide_url")
    public val hideUrl: Boolean? = null,
    /**
     * *Optional*. Short description of the result
     */
    @get:JsonProperty("description")
    @param:JsonProperty("description")
    public val description: String? = null,
    /**
     * *Optional*. Url of the thumbnail for the result
     */
    @get:JsonProperty("thumbnail_url")
    @param:JsonProperty("thumbnail_url")
    public val thumbnailUrl: String? = null,
    /**
     * *Optional*. Thumbnail width
     */
    @get:JsonProperty("thumbnail_width")
    @param:JsonProperty("thumbnail_width")
    public val thumbnailWidth: Int? = null,
    /**
     * *Optional*. Thumbnail height
     */
    @get:JsonProperty("thumbnail_height")
    @param:JsonProperty("thumbnail_height")
    public val thumbnailHeight: Int? = null,
) : InlineQueryResult {
    @get:JsonProperty("type")
    override val type: String = "article"
}
