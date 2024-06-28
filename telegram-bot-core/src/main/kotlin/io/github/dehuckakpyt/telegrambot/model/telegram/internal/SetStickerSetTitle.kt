package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * @author KScript
 */
internal data class SetStickerSetTitle(
    @get:JsonProperty("name")
    public val name: String,
    @get:JsonProperty("title")
    public val title: String,
)
