package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * This object represents an inline keyboard button that copies specified text to the clipboard.
 *
 * @see [CopyTextButton] (https://core.telegram.org/bots/api/#copytextbutton)
 *
 * @author KScript
 *
 * @param text The text to be copied to the clipboard; 1-256 characters
 */
public data class CopyTextButton(
    /**
     * The text to be copied to the clipboard; 1-256 characters
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String,
)
