package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * @author KScript
 */
internal data class GetFile(
    @get:JsonProperty("file_id")
    public val fileId: String,
)
