package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.KeyboardButton
import kotlin.Long

/**
 * @author KScript
 */
internal data class SavePreparedKeyboardButton(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("button")
    public val button: KeyboardButton,
)
