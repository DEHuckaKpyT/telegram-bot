package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * The message was originally sent by an unknown user.
 *
 * @see [MessageOriginHiddenUser] (https://core.telegram.org/bots/api/#messageoriginhiddenuser)
 *
 * @author KScript
 *
 * @param type Type of the message origin, always “hidden_user”
 * @param date Date the message was sent originally in Unix time
 * @param senderUserName Name of the user that sent the message originally
 */
public data class MessageOriginHiddenUser(
    /**
     * Type of the message origin, always “hidden_user”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Date the message was sent originally in Unix time
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    override val date: Long,
    /**
     * Name of the user that sent the message originally
     */
    @get:JsonProperty("sender_user_name")
    @param:JsonProperty("sender_user_name")
    public val senderUserName: String,
) : MessageOrigin
