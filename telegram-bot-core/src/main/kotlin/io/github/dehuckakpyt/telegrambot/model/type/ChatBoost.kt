package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName


/**
 * Created on 12.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
public data class ChatBoostUpdated(
    @param:JsonProperty("chat") val chat: Chat,
    @param:JsonProperty("boost") val boost: ChatBoost,
)

public data class ChatBoostRemoved(
    @param:JsonProperty("chat") val chat: Chat,
    @param:JsonProperty("boost_id") val boostId: String,
    @param:JsonProperty("remove_date") val removeDate: Long,
    @param:JsonProperty("source") val source: ChatBoostSource,
)

public data class ChatBoost(
    @param:JsonProperty("boost_id") val boostId: String,
    @param:JsonProperty("add_date") val addDate: Long,
    @param:JsonProperty("expiration_date") val expirationDate: Long,
    @param:JsonProperty("source") val source: ChatBoostSource,
)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "source", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = ChatBoostSource.ChatBoostSourcePremium::class, name = "premium"),
    JsonSubTypes.Type(value = ChatBoostSource.ChatBoostSourceGiftCode::class, name = "gift_code"),
    JsonSubTypes.Type(value = ChatBoostSource.ChatBoostSourceGiveaway::class, name = "giveaway"),
)
public sealed class ChatBoostSource {

    public abstract val source: String

    @JsonTypeName("premium")
    data class ChatBoostSourcePremium(
        @param:JsonProperty("source") override val source: String = "premium",
        @param:JsonProperty("user") val user: User,
    ) : ChatBoostSource()

    @JsonTypeName("gift_code")
    data class ChatBoostSourceGiftCode(
        @param:JsonProperty("source") override val source: String = "gift_code",
        @param:JsonProperty("user") val user: User,
    ) : ChatBoostSource()

    @JsonTypeName("giveaway")
    data class ChatBoostSourceGiveaway(
        @param:JsonProperty("source") override val source: String = "giveaway",
        @param:JsonProperty("giveaway_message_id") val giveawayMessageId: Long,
        @param:JsonProperty("user") val user: User? = null,
        @param:JsonProperty("is_unclaimed") val isUnclaimed: Boolean? = null,
    ) : ChatBoostSource()
}

public data class UserChatBoosts(
    @param:JsonProperty("boosts") val boosts: List<ChatBoost>,
)

public data class ChatBoostAdded(
    @param:JsonProperty("boost_count") val boostCount: Int,
)