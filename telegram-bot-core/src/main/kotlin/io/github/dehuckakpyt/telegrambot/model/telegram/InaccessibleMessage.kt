package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * Created on 02.06.2024.
 *
 * This object describes a message that was deleted or is otherwise inaccessible to the bot.
 *
 * @see [InaccessibleMessage] (https://core.telegram.org/bots/api/#inaccessiblemessage)
 *
 * @author KScript
 *
 * @param chat Chat the message belonged to
 * @param messageId Unique message identifier inside the chat
 * @param date Always 0. The field can be used to differentiate regular and inaccessible messages.
 */
public data class InaccessibleMessage(
    /**
     * Chat the message belonged to
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    override val chat: Chat,
    /**
     * Unique message identifier inside the chat
     */
    @get:JsonProperty("message_id")
    @param:JsonProperty("message_id")
    override val messageId: Long,
    /**
     * Always 0. The field can be used to differentiate regular and inaccessible messages.
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    override val date: Long,
) : MaybeInaccessibleMessage
