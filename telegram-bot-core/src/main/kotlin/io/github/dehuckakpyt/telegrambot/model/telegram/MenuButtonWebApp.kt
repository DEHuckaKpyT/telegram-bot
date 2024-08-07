package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents a menu button, which launches a [Web App](https://core.telegram.org/bots/webapps).
 *
 * @see [MenuButtonWebApp] (https://core.telegram.org/bots/api/#menubuttonwebapp)
 *
 * @author KScript
 *
 * @param text Text on the button
 * @param webApp Description of the Web App that will be launched when the user presses the button.
 * The Web App will be able to send an arbitrary message on behalf of the user using the method
 * [answerWebAppQuery](https://core.telegram.org/bots/api/#answerwebappquery). Alternatively, a `t.me`
 * link to a Web App of the bot can be specified in the object instead of the Web App's URL, in which
 * case the Web App will be opened as if the user pressed the link.
 */
public data class MenuButtonWebApp(
    /**
     * Text on the button
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String,
    /**
     * Description of the Web App that will be launched when the user presses the button. The Web
     * App will be able to send an arbitrary message on behalf of the user using the method
     * [answerWebAppQuery](https://core.telegram.org/bots/api/#answerwebappquery). Alternatively, a
     * `t.me` link to a Web App of the bot can be specified in the object instead of the Web App's URL,
     * in which case the Web App will be opened as if the user pressed the link.
     */
    @get:JsonProperty("web_app")
    @param:JsonProperty("web_app")
    public val webApp: WebAppInfo,
) : MenuButton {
    @get:JsonProperty("type")
    override val type: String = "web_app"
}
