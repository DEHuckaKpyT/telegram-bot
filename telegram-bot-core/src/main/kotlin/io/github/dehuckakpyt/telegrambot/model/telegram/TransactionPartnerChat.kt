package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes a transaction with a chat.
 *
 * @see [TransactionPartnerChat] (https://core.telegram.org/bots/api/#transactionpartnerchat)
 *
 * @author KScript
 *
 * @param type Type of the transaction partner, always “chat”
 * @param chat Information about the chat
 * @param gift *Optional*. The gift sent to the chat by the bot
 */
public data class TransactionPartnerChat(
    /**
     * Type of the transaction partner, always “chat”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Information about the chat
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * *Optional*. The gift sent to the chat by the bot
     */
    @get:JsonProperty("gift")
    @param:JsonProperty("gift")
    public val gift: Gift? = null,
) : TransactionPartner
