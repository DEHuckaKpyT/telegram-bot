package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty

/**
 * Caption of a rich formatted block.
 *
 * @see [RichBlockCaption] (https://core.telegram.org/bots/api/#richblockcaption)
 *
 * @author KScript
 *
 * @param text Block caption
 * @param credit *Optional*. Block credit which corresponds to the HTML tag \<cite\>
 */
public data class RichBlockCaption(
    /**
     * Block caption
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText,
    /**
     * *Optional*. Block credit which corresponds to the HTML tag \<cite\>
     */
    @get:JsonProperty("credit")
    @param:JsonProperty("credit")
    public val credit: RichText? = null,
)
