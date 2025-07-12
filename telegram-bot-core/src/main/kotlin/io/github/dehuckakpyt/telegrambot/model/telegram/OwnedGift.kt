package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * This object describes a gift received and owned by a user or a chat. Currently, it can be one of
 *
 * * [OwnedGiftRegular](https://core.telegram.org/bots/api/#ownedgiftregular)
 * * [OwnedGiftUnique](https://core.telegram.org/bots/api/#ownedgiftunique)
 *
 * @see [OwnedGift] (https://core.telegram.org/bots/api/#ownedgift)
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
    JsonSubTypes.Type(value = OwnedGiftRegular::class, name = "regular"),
    JsonSubTypes.Type(value = OwnedGiftUnique::class, name = "unique"),
)
public sealed interface OwnedGift {
    public val type: String

    public val ownedGiftId: String?

    public val senderUser: User?

    public val sendDate: Long

    public val isSaved: Boolean?
}
