package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * The reaction is based on a custom emoji.
 *
 * @see [ReactionTypeCustomEmoji] (https://core.telegram.org/bots/api/#reactiontypecustomemoji)
 *
 * @author KScript
 *
 * @param type Type of the reaction, always “custom_emoji”
 * @param customEmojiId Custom emoji identifier
 */
public data class ReactionTypeCustomEmoji(
    /**
     * Type of the reaction, always “custom_emoji”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Custom emoji identifier
     */
    @get:JsonProperty("custom_emoji_id")
    @param:JsonProperty("custom_emoji_id")
    public val customEmojiId: String,
) : ReactionType
