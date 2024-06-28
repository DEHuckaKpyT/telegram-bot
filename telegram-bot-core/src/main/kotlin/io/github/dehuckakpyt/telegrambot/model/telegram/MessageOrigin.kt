package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.Long
import kotlin.String

/**
 * This object describes the origin of a message. It can be one of
 *
 * * [MessageOriginUser](https://core.telegram.org/bots/api/#messageoriginuser)
 * * [MessageOriginHiddenUser](https://core.telegram.org/bots/api/#messageoriginhiddenuser)
 * * [MessageOriginChat](https://core.telegram.org/bots/api/#messageoriginchat)
 * * [MessageOriginChannel](https://core.telegram.org/bots/api/#messageoriginchannel)
 *
 * @see [MessageOrigin] (https://core.telegram.org/bots/api/#messageorigin)
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
    JsonSubTypes.Type(value = MessageOriginUser::class, name = "user"),
    JsonSubTypes.Type(value = MessageOriginHiddenUser::class, name = "hidden_user"),
    JsonSubTypes.Type(value = MessageOriginChat::class, name = "chat"),
    JsonSubTypes.Type(value = MessageOriginChannel::class, name = "channel"),
)
public sealed interface MessageOrigin {
    public val type: String

    public val date: Long
}
