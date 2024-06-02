package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.BotCommandScope
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class DeleteMyCommands(
    @get:JsonProperty("scope")
    public val scope: BotCommandScope? = null,
    @get:JsonProperty("language_code")
    public val languageCode: String? = null,
)
