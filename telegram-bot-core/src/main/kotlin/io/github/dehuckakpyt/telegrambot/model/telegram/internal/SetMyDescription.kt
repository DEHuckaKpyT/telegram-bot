package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class SetMyDescription(
    @get:JsonProperty("description")
    public val description: String? = null,
    @get:JsonProperty("language_code")
    public val languageCode: String? = null,
)
