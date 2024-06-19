package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents a menu button, which opens the bot's list of commands.
 *
 * @see [MenuButtonCommands] (https://core.telegram.org/bots/api/#menubuttoncommands)
 *
 * @author KScript
 */
public class MenuButtonCommands() : MenuButton {
    @get:JsonProperty("type")
    override val type: String = "commands"
}
