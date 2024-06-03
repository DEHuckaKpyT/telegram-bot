package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * Represents a join request sent to a chat.
 *
 * @see [ChatJoinRequest] (https://core.telegram.org/bots/api/#chatjoinrequest)
 *
 * @author KScript
 *
 * @param chat Chat to which the request was sent
 * @param from User that sent the join request
 * @param userChatId Identifier of a private chat with the user who sent the join request. This
 * number may have more than 32 significant bits and some programming languages may have
 * difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a 64-bit
 * integer or double-precision float type are safe for storing this identifier. The bot can use this
 * identifier for 5 minutes to send messages until the join request is processed, assuming no other
 * administrator contacted the user.
 * @param date Date the request was sent in Unix time
 * @param bio *Optional*. Bio of the user.
 * @param inviteLink *Optional*. Chat invite link that was used by the user to send the join request
 */
public data class ChatJoinRequest(
    /**
     * Chat to which the request was sent
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * User that sent the join request
     */
    @get:JsonProperty("from")
    @param:JsonProperty("from")
    public val from: User,
    /**
     * Identifier of a private chat with the user who sent the join request. This number may have
     * more than 32 significant bits and some programming languages may have difficulty/silent defects
     * in interpreting it. But it has at most 52 significant bits, so a 64-bit integer or
     * double-precision float type are safe for storing this identifier. The bot can use this
     * identifier for 5 minutes to send messages until the join request is processed, assuming no other
     * administrator contacted the user.
     */
    @get:JsonProperty("user_chat_id")
    @param:JsonProperty("user_chat_id")
    public val userChatId: Long,
    /**
     * Date the request was sent in Unix time
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    public val date: Long,
    /**
     * *Optional*. Bio of the user.
     */
    @get:JsonProperty("bio")
    @param:JsonProperty("bio")
    public val bio: String? = null,
    /**
     * *Optional*. Chat invite link that was used by the user to send the join request
     */
    @get:JsonProperty("invite_link")
    @param:JsonProperty("invite_link")
    public val inviteLink: ChatInviteLink? = null,
)
