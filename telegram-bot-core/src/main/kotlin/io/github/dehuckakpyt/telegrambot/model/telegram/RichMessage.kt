package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.collections.List

/**
 * Rich formatted message.
 *
 * @see [RichMessage] (https://core.telegram.org/bots/api/#richmessage)
 *
 * @author KScript
 *
 * @param blocks Content of the message
 * @param isRtl *Optional*. *True*, if the rich message must be shown right-to-left
 */
public data class RichMessage(
    /**
     * Content of the message
     */
    @get:JsonProperty("blocks")
    @param:JsonProperty("blocks")
    public val blocks: List<RichBlock>,
    /**
     * *Optional*. *True*, if the rich message must be shown right-to-left
     */
    @get:JsonProperty("is_rtl")
    @param:JsonProperty("is_rtl")
    public val isRtl: Boolean? = null,
)
