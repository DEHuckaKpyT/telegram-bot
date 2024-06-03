package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * Represents the [scope](https://core.telegram.org/bots/api/#botcommandscope) of bot commands,
 * covering all group and supergroup chat administrators.
 *
 * @see [BotCommandScopeAllChatAdministrators]
 * (https://core.telegram.org/bots/api/#botcommandscopeallchatadministrators)
 *
 * @author KScript
 */
public class BotCommandScopeAllChatAdministrators() : BotCommandScope {
    @get:JsonProperty("type")
    override val type: String = "all_chat_administrators"
}
