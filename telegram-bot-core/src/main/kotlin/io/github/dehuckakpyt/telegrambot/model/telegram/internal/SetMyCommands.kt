package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.BotCommand
import io.github.dehuckakpyt.telegrambot.model.telegram.BotCommandScope
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class SetMyCommands(
    @get:JsonProperty("commands")
    public val commands: Iterable<BotCommand>,
    @get:JsonProperty("scope")
    public val scope: BotCommandScope? = null,
    @get:JsonProperty("language_code")
    public val languageCode: String? = null,
)
