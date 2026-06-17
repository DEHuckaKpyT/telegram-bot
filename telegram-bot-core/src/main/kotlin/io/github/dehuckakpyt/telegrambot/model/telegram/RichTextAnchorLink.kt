package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A link to an anchor.
 *
 * @see [RichTextAnchorLink] (https://core.telegram.org/bots/api/#richtextanchorlink)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “anchor_link”
 * @param text The link text
 * @param anchorName The name of the anchor. If the name is empty, then the link brings back to the
 * top of the message.
 */
public data class RichTextAnchorLink(
    /**
     * Type of the rich text, always “anchor_link”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The link text
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText,
    /**
     * The name of the anchor. If the name is empty, then the link brings back to the top of the
     * message.
     */
    @get:JsonProperty("anchor_name")
    @param:JsonProperty("anchor_name")
    public val anchorName: String,
) : RichText
