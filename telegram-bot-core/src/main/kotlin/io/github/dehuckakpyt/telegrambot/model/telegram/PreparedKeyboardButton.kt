package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes a keyboard button to be used by a user of a Mini App.
 *
 * @see [PreparedKeyboardButton] (https://core.telegram.org/bots/api/#preparedkeyboardbutton)
 *
 * @author KScript
 *
 * @param id Unique identifier of the keyboard button
 */
public data class PreparedKeyboardButton(
    /**
     * Unique identifier of the keyboard button
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: String,
)
