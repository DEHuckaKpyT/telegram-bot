package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes an inline message sent by a guest bot.
 *
 * @see [SentGuestMessage] (https://core.telegram.org/bots/api/#sentguestmessage)
 *
 * @author KScript
 *
 * @param inlineMessageId Identifier of the sent inline message
 */
public data class SentGuestMessage(
    /**
     * Identifier of the sent inline message
     */
    @get:JsonProperty("inline_message_id")
    @param:JsonProperty("inline_message_id")
    public val inlineMessageId: String,
)
