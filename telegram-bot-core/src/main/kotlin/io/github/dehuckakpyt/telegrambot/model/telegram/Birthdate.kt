package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * Created on 02.06.2024.
 *
 * Describes the birthdate of a user.
 *
 * @see [Birthdate] (https://core.telegram.org/bots/api/#birthdate)
 *
 * @author KScript
 *
 * @param day Day of the user's birth; 1-31
 * @param month Month of the user's birth; 1-12
 * @param year *Optional*. Year of the user's birth
 */
public data class Birthdate(
    /**
     * Day of the user's birth; 1-31
     */
    @get:JsonProperty("day")
    @param:JsonProperty("day")
    public val day: Int,
    /**
     * Month of the user's birth; 1-12
     */
    @get:JsonProperty("month")
    @param:JsonProperty("month")
    public val month: Int,
    /**
     * *Optional*. Year of the user's birth
     */
    @get:JsonProperty("year")
    @param:JsonProperty("year")
    public val year: Int? = null,
)
