package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
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
