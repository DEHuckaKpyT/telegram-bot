package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * The background is a freeform gradient that rotates after every message in the chat.
 *
 * @see [BackgroundFillFreeformGradient]
 * (https://core.telegram.org/bots/api/#backgroundfillfreeformgradient)
 *
 * @author KScript
 *
 * @param type Type of the background fill, always “freeform_gradient”
 * @param colors A list of the 3 or 4 base colors that are used to generate the freeform gradient in
 * the RGB24 format
 */
public data class BackgroundFillFreeformGradient(
    /**
     * Type of the background fill, always “freeform_gradient”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * A list of the 3 or 4 base colors that are used to generate the freeform gradient in the RGB24
     * format
     */
    @get:JsonProperty("colors")
    @param:JsonProperty("colors")
    public val colors: List<Int>,
) : BackgroundFill
