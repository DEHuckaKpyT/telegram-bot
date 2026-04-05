package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty

/**
 * This object contains information about the creation or token update of a bot that is managed by
 * the current bot.
 *
 * @see [ManagedBotUpdated] (https://core.telegram.org/bots/api/#managedbotupdated)
 *
 * @author KScript
 *
 * @param user User that created the bot
 * @param bot Information about the bot. Token of the bot can be fetched using the method
 * [getManagedBotToken](https://core.telegram.org/bots/api/#getmanagedbottoken).
 */
public data class ManagedBotUpdated(
    /**
     * User that created the bot
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    public val user: User,
    /**
     * Information about the bot. Token of the bot can be fetched using the method
     * [getManagedBotToken](https://core.telegram.org/bots/api/#getmanagedbottoken).
     */
    @get:JsonProperty("bot")
    @param:JsonProperty("bot")
    public val bot: User,
)
