package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * The background is filled using the selected color.
 *
 * @see [BackgroundFillSolid] (https://core.telegram.org/bots/api/#backgroundfillsolid)
 *
 * @author KScript
 *
 * @param type Type of the background fill, always “solid”
 * @param color The color of the background fill in the RGB24 format
 */
public data class BackgroundFillSolid(
    /**
     * Type of the background fill, always “solid”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The color of the background fill in the RGB24 format
     */
    @get:JsonProperty("color")
    @param:JsonProperty("color")
    public val color: Int,
) : BackgroundFill
