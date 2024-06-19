package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes that no specific value for the menu button was set.
 *
 * @see [MenuButtonDefault] (https://core.telegram.org/bots/api/#menubuttondefault)
 *
 * @author KScript
 */
public class MenuButtonDefault() : MenuButton {
    @get:JsonProperty("type")
    override val type: String = "default"
}
