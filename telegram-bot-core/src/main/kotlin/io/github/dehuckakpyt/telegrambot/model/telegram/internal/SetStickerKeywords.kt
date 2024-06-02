package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.Iterable

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class SetStickerKeywords(
    @get:JsonProperty("sticker")
    public val sticker: String,
    @get:JsonProperty("keywords")
    public val keywords: Iterable<String>? = null,
)
