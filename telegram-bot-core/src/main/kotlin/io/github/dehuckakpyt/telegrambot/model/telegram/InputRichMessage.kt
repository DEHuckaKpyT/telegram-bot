package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * Describes a rich message to be sent. Exactly **one** of the fields *html* or *markdown* must be
 * used.
 *
 * @see [InputRichMessage] (https://core.telegram.org/bots/api/#inputrichmessage)
 *
 * @author KScript
 *
 * @param html *Optional*. Content of the rich message to send described using HTML formatting. See
 * [rich message formatting
 * options](https://core.telegram.org/bots/api/#rich-message-formatting-options) for more details.
 * @param markdown *Optional*. Content of the rich message to send described using Markdown
 * formatting. See [rich message formatting
 * options](https://core.telegram.org/bots/api/#rich-message-formatting-options) for more details.
 * @param isRtl *Optional*. Pass *True* if the rich message must be shown right-to-left
 * @param skipEntityDetection *Optional*. Pass *True* to skip automatic detection of entities (e.g.,
 * URLs, email addresses, username mentions, hashtags, cashtags, bot commands, or phone numbers) in the
 * text
 */
public data class InputRichMessage(
    /**
     * *Optional*. Content of the rich message to send described using HTML formatting. See [rich
     * message formatting options](https://core.telegram.org/bots/api/#rich-message-formatting-options)
     * for more details.
     */
    @get:JsonProperty("html")
    @param:JsonProperty("html")
    public val html: String? = null,
    /**
     * *Optional*. Content of the rich message to send described using Markdown formatting. See
     * [rich message formatting
     * options](https://core.telegram.org/bots/api/#rich-message-formatting-options) for more details.
     */
    @get:JsonProperty("markdown")
    @param:JsonProperty("markdown")
    public val markdown: String? = null,
    /**
     * *Optional*. Pass *True* if the rich message must be shown right-to-left
     */
    @get:JsonProperty("is_rtl")
    @param:JsonProperty("is_rtl")
    public val isRtl: Boolean? = null,
    /**
     * *Optional*. Pass *True* to skip automatic detection of entities (e.g., URLs, email addresses,
     * username mentions, hashtags, cashtags, bot commands, or phone numbers) in the text
     */
    @get:JsonProperty("skip_entity_detection")
    @param:JsonProperty("skip_entity_detection")
    public val skipEntityDetection: Boolean? = null,
)
