package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * @author KScript
 */
internal data class GetMyName(
    @get:JsonProperty("language_code")
    public val languageCode: String? = null,
)
