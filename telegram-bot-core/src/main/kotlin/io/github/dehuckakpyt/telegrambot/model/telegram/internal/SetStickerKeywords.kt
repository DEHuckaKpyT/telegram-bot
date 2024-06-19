package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class SetStickerKeywords(
    @get:JsonProperty("sticker")
    public val sticker: String,
    @get:JsonProperty("keywords")
    public val keywords: Iterable<String>? = null,
)
