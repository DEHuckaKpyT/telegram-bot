package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class DeleteStickerSet(
    @get:JsonProperty("name")
    public val name: String,
)
