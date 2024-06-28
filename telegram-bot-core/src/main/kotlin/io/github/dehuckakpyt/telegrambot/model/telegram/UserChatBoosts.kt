package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.collections.List

/**
 * This object represents a list of boosts added to a chat by a user.
 *
 * @see [UserChatBoosts] (https://core.telegram.org/bots/api/#userchatboosts)
 *
 * @author KScript
 *
 * @param boosts The list of boosts added to the chat by the user
 */
public data class UserChatBoosts(
    /**
     * The list of boosts added to the chat by the user
     */
    @get:JsonProperty("boosts")
    @param:JsonProperty("boosts")
    public val boosts: List<ChatBoost>,
)
