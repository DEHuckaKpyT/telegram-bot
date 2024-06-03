package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * The message was originally sent by a known user.
 *
 * @see [MessageOriginUser] (https://core.telegram.org/bots/api/#messageoriginuser)
 *
 * @author KScript
 *
 * @param type Type of the message origin, always “user”
 * @param date Date the message was sent originally in Unix time
 * @param senderUser User that sent the message originally
 */
public data class MessageOriginUser(
    /**
     * Type of the message origin, always “user”
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
     * User that sent the message originally
     */
    @get:JsonProperty("sender_user")
    @param:JsonProperty("sender_user")
    public val senderUser: User,
) : MessageOrigin
