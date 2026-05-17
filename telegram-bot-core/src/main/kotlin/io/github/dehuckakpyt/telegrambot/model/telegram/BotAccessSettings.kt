package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.collections.List

/**
 * This object describes the access settings of a bot.
 *
 * @see [BotAccessSettings] (https://core.telegram.org/bots/api/#botaccesssettings)
 *
 * @author KScript
 *
 * @param isAccessRestricted *True*, if only selected users can access the bot. The bot's owner can
 * always access it.
 * @param addedUsers *Optional*. The list of other users who have access to the bot if the access is
 * restricted
 */
public data class BotAccessSettings(
    /**
     * *True*, if only selected users can access the bot. The bot's owner can always access it.
     */
    @get:JsonProperty("is_access_restricted")
    @param:JsonProperty("is_access_restricted")
    public val isAccessRestricted: Boolean,
    /**
     * *Optional*. The list of other users who have access to the bot if the access is restricted
     */
    @get:JsonProperty("added_users")
    @param:JsonProperty("added_users")
    public val addedUsers: List<User>? = null,
)
