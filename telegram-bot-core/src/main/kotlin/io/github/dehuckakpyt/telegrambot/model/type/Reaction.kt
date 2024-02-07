package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName


/**
 * Created on 11.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = ReactionType.Emoji::class, name = "emoji"),
    JsonSubTypes.Type(value = ReactionType.CustomEmoji::class, name = "custom_emoji"),
)
public sealed class ReactionType {
    abstract val type: String

    @JsonTypeName("emoji")
    public data class Emoji(
        @param:JsonProperty("emoji") @get:JsonProperty("emoji") val emoji: String,
    ) : ReactionType() {
        @get:JsonProperty("type") override val type: String = "emoji"
    }

    @JsonTypeName("custom_emoji")
    public data class CustomEmoji(
        @param:JsonProperty("custom_emoji_id") @get:JsonProperty("custom_emoji_id") val customEmojiId: String,
    ) : ReactionType() {
        @get:JsonProperty("type") override val type: String = "custom_emoji"
    }
}

public data class ReactionCount(
    @param:JsonProperty("type") val type: ReactionType,
    @param:JsonProperty("total_count") val totalCount: Long,
)

public data class MessageReactionUpdated(
    @param:JsonProperty("chat") val chat: Chat,
    @param:JsonProperty("message_id") val messageId: Long,
    @param:JsonProperty("user") val user: User?,
    @param:JsonProperty("actor_chat") val actorChat: Chat,
    @param:JsonProperty("date") val date: Long,
    @param:JsonProperty("old_reaction") val oldReaction: List<ReactionType>,
    @param:JsonProperty("new_reaction") val newReaction: List<ReactionType>,
)

public data class MessageReactionCountUpdated(
    @param:JsonProperty("chat") val chat: Chat,
    @param:JsonProperty("message_id") val messageId: Long,
    @param:JsonProperty("date") val date: Long,
    @param:JsonProperty("reactions") val reactions: List<ReactionCount>,
)