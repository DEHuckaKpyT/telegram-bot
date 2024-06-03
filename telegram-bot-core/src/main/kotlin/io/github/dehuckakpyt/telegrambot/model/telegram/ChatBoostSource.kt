package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * This object describes the source of a chat boost. It can be one of
 *
 * * [ChatBoostSourcePremium](https://core.telegram.org/bots/api/#chatboostsourcepremium)
 * * [ChatBoostSourceGiftCode](https://core.telegram.org/bots/api/#chatboostsourcegiftcode)
 * * [ChatBoostSourceGiveaway](https://core.telegram.org/bots/api/#chatboostsourcegiveaway)
 *
 * @see [ChatBoostSource] (https://core.telegram.org/bots/api/#chatboostsource)
 *
 * @author KScript
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "source",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = ChatBoostSourcePremium::class, name = "premium"),
    JsonSubTypes.Type(value = ChatBoostSourceGiftCode::class, name = "gift_code"),
    JsonSubTypes.Type(value = ChatBoostSourceGiveaway::class, name = "giveaway"),
)
public sealed interface ChatBoostSource {
    public val source: String

    public val user: User?
}
