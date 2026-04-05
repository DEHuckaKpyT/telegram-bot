package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty

/**
 * This object contains information about the bot that was created to be managed by the current bot.
 *
 * @see [ManagedBotCreated] (https://core.telegram.org/bots/api/#managedbotcreated)
 *
 * @author KScript
 *
 * @param bot Information about the bot. The bot's token can be fetched using the method
 * [getManagedBotToken](https://core.telegram.org/bots/api/#getmanagedbottoken).
 */
public data class ManagedBotCreated(
    /**
     * Information about the bot. The bot's token can be fetched using the method
     * [getManagedBotToken](https://core.telegram.org/bots/api/#getmanagedbottoken).
     */
    @get:JsonProperty("bot")
    @param:JsonProperty("bot")
    public val bot: User,
)
