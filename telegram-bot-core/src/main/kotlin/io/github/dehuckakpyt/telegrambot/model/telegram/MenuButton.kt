package io.github.dehuckakpyt.telegrambot.model.telegram

import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * This object describes the bot's menu button in a private chat. It should be one of
 *
 * * [MenuButtonCommands](https://core.telegram.org/bots/api/#menubuttoncommands)
 * * [MenuButtonWebApp](https://core.telegram.org/bots/api/#menubuttonwebapp)
 * * [MenuButtonDefault](https://core.telegram.org/bots/api/#menubuttondefault)
 *
 * @see [MenuButton] (https://core.telegram.org/bots/api/#menubutton)
 *
 * @author KScript
 */
public sealed interface MenuButton {
    public val type: String
}
