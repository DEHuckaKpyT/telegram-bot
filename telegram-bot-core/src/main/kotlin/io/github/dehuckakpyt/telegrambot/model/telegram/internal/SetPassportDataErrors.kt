package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.PassportElementError
import kotlin.Long
import kotlin.collections.Iterable

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class SetPassportDataErrors(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("errors")
    public val errors: Iterable<PassportElementError>,
)
