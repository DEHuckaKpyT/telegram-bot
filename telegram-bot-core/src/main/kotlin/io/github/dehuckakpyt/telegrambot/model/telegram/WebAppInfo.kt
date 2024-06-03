package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * Describes a [Web App](https://core.telegram.org/bots/webapps).
 *
 * @see [WebAppInfo] (https://core.telegram.org/bots/api/#webappinfo)
 *
 * @author KScript
 *
 * @param url An HTTPS URL of a Web App to be opened with additional data as specified in
 * [Initializing Web Apps](https://core.telegram.org/bots/webapps#initializing-mini-apps)
 */
public data class WebAppInfo(
    /**
     * An HTTPS URL of a Web App to be opened with additional data as specified in [Initializing Web
     * Apps](https://core.telegram.org/bots/webapps#initializing-mini-apps)
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String,
)
