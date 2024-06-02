package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.Long

/**
 * Created on 02.06.2024.
 *
 * This object describes a message that can be inaccessible to the bot. It can be one of
 *
 * * [Message](https://core.telegram.org/bots/api/#message)
 * * [InaccessibleMessage](https://core.telegram.org/bots/api/#inaccessiblemessage)
 *
 * @see [MaybeInaccessibleMessage] (https://core.telegram.org/bots/api/#maybeinaccessiblemessage)
 *
 * @author KScript
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "date",
    visible = true,
    defaultImpl = Message::class,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = InaccessibleMessage::class, name = "0"),
)
public sealed interface MaybeInaccessibleMessage {
    public val messageId: Long

    public val date: Long

    public val chat: Chat
}
