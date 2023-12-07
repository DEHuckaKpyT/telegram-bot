package io.github.dehuckakpyt.telegrambot.model.internal

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.PassportElementError


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
internal class SetPassportDataErrors(
    @get:JsonProperty("user_id") val userId: Long,
    @get:JsonProperty("errors") val errors: List<PassportElementError>
)