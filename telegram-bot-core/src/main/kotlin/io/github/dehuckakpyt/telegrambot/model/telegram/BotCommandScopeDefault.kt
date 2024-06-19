package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents the default [scope](https://core.telegram.org/bots/api/#botcommandscope) of bot
 * commands. Default commands are used if no commands with a [narrower
 * scope](https://core.telegram.org/bots/api/#determining-list-of-commands) are specified for the user.
 *
 * @see [BotCommandScopeDefault] (https://core.telegram.org/bots/api/#botcommandscopedefault)
 *
 * @author KScript
 */
public class BotCommandScopeDefault() : BotCommandScope {
    @get:JsonProperty("type")
    override val type: String = "default"
}
