package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * The reaction is paid.
 *
 * @see [ReactionTypePaid] (https://core.telegram.org/bots/api/#reactiontypepaid)
 *
 * @author KScript
 *
 * @param type Type of the reaction, always “paid”
 */
public data class ReactionTypePaid(
    /**
     * Type of the reaction, always “paid”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
) : ReactionType
