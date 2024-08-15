package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * This object describes the type of a reaction. Currently, it can be one of
 *
 * * [ReactionTypeEmoji](https://core.telegram.org/bots/api/#reactiontypeemoji)
 * * [ReactionTypeCustomEmoji](https://core.telegram.org/bots/api/#reactiontypecustomemoji)
 * * [ReactionTypePaid](https://core.telegram.org/bots/api/#reactiontypepaid)
 *
 * @see [ReactionType] (https://core.telegram.org/bots/api/#reactiontype)
 *
 * @author KScript
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = ReactionTypeEmoji::class, name = "emoji"),
    JsonSubTypes.Type(value = ReactionTypeCustomEmoji::class, name = "custom_emoji"),
    JsonSubTypes.Type(value = ReactionTypePaid::class, name = "paid"),
)
public sealed interface ReactionType {
    public val type: String
}
