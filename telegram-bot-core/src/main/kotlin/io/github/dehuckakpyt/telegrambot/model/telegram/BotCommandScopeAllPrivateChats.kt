package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * Represents the [scope](https://core.telegram.org/bots/api/#botcommandscope) of bot commands,
 * covering all private chats.
 *
 * @see [BotCommandScopeAllPrivateChats]
 * (https://core.telegram.org/bots/api/#botcommandscopeallprivatechats)
 *
 * @author KScript
 */
public class BotCommandScopeAllPrivateChats() : BotCommandScope {
    @get:JsonProperty("type")
    override val type: String = "all_private_chats"
}
