package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * Describes the options used for link preview generation.
 *
 * @see [LinkPreviewOptions] (https://core.telegram.org/bots/api/#linkpreviewoptions)
 *
 * @author KScript
 *
 * @param isDisabled *Optional*. *True*, if the link preview is disabled
 * @param url *Optional*. URL to use for the link preview. If empty, then the first URL found in the
 * message text will be used
 * @param preferSmallMedia *Optional*. *True*, if the media in the link preview is supposed to be
 * shrunk; ignored if the URL isn't explicitly specified or media size change isn't supported for the
 * preview
 * @param preferLargeMedia *Optional*. *True*, if the media in the link preview is supposed to be
 * enlarged; ignored if the URL isn't explicitly specified or media size change isn't supported for the
 * preview
 * @param showAboveText *Optional*. *True*, if the link preview must be shown above the message
 * text; otherwise, the link preview will be shown below the message text
 */
public data class LinkPreviewOptions(
    /**
     * *Optional*. *True*, if the link preview is disabled
     */
    @get:JsonProperty("is_disabled")
    @param:JsonProperty("is_disabled")
    public val isDisabled: Boolean? = null,
    /**
     * *Optional*. URL to use for the link preview. If empty, then the first URL found in the
     * message text will be used
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String? = null,
    /**
     * *Optional*. *True*, if the media in the link preview is supposed to be shrunk; ignored if the
     * URL isn't explicitly specified or media size change isn't supported for the preview
     */
    @get:JsonProperty("prefer_small_media")
    @param:JsonProperty("prefer_small_media")
    public val preferSmallMedia: Boolean? = null,
    /**
     * *Optional*. *True*, if the media in the link preview is supposed to be enlarged; ignored if
     * the URL isn't explicitly specified or media size change isn't supported for the preview
     */
    @get:JsonProperty("prefer_large_media")
    @param:JsonProperty("prefer_large_media")
    public val preferLargeMedia: Boolean? = null,
    /**
     * *Optional*. *True*, if the link preview must be shown above the message text; otherwise, the
     * link preview will be shown below the message text
     */
    @get:JsonProperty("show_above_text")
    @param:JsonProperty("show_above_text")
    public val showAboveText: Boolean? = null,
)
