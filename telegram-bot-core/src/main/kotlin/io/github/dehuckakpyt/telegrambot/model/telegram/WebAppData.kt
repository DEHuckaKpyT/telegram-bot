package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes data sent from a [Web App](https://core.telegram.org/bots/webapps) to the bot.
 *
 * @see [WebAppData] (https://core.telegram.org/bots/api/#webappdata)
 *
 * @author KScript
 *
 * @param data The data. Be aware that a bad client can send arbitrary data in this field.
 * @param buttonText Text of the *web_app* keyboard button from which the Web App was opened. Be
 * aware that a bad client can send arbitrary data in this field.
 */
public data class WebAppData(
    /**
     * The data. Be aware that a bad client can send arbitrary data in this field.
     */
    @get:JsonProperty("data")
    @param:JsonProperty("data")
    public val `data`: String,
    /**
     * Text of the *web_app* keyboard button from which the Web App was opened. Be aware that a bad
     * client can send arbitrary data in this field.
     */
    @get:JsonProperty("button_text")
    @param:JsonProperty("button_text")
    public val buttonText: String,
)
