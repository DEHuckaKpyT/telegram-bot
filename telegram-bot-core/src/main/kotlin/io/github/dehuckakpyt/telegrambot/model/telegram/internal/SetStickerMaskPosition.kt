package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.MaskPosition
import kotlin.String

/**
 * @author KScript
 */
internal data class SetStickerMaskPosition(
    @get:JsonProperty("sticker")
    public val sticker: String,
    @get:JsonProperty("mask_position")
    public val maskPosition: MaskPosition? = null,
)
