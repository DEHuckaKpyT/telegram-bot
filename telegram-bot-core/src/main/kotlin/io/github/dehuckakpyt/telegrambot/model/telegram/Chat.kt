package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * This object represents a chat.
 *
 * @see [Chat] (https://core.telegram.org/bots/api/#chat)
 *
 * @author KScript
 *
 * @param id Unique identifier for this chat. This number may have more than 32 significant bits and
 * some programming languages may have difficulty/silent defects in interpreting it. But it has at most
 * 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for storing
 * this identifier.
 * @param type Type of the chat, can be either “private”, “group”, “supergroup” or “channel”
 * @param title *Optional*. Title, for supergroups, channels and group chats
 * @param username *Optional*. Username, for private chats, supergroups and channels if available
 * @param firstName *Optional*. First name of the other party in a private chat
 * @param lastName *Optional*. Last name of the other party in a private chat
 * @param isForum *Optional*. *True*, if the supergroup chat is a forum (has
 * [topics](https://telegram.org/blog/topics-in-groups-collectible-usernames#topics-in-groups) enabled)
 */
public data class Chat(
    /**
     * Unique identifier for this chat. This number may have more than 32 significant bits and some
     * programming languages may have difficulty/silent defects in interpreting it. But it has at most
     * 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for
     * storing this identifier.
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: Long,
    /**
     * Type of the chat, can be either “private”, “group”, “supergroup” or “channel”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: String,
    /**
     * *Optional*. Title, for supergroups, channels and group chats
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String? = null,
    /**
     * *Optional*. Username, for private chats, supergroups and channels if available
     */
    @get:JsonProperty("username")
    @param:JsonProperty("username")
    public val username: String? = null,
    /**
     * *Optional*. First name of the other party in a private chat
     */
    @get:JsonProperty("first_name")
    @param:JsonProperty("first_name")
    public val firstName: String? = null,
    /**
     * *Optional*. Last name of the other party in a private chat
     */
    @get:JsonProperty("last_name")
    @param:JsonProperty("last_name")
    public val lastName: String? = null,
    /**
     * *Optional*. *True*, if the supergroup chat is a forum (has
     * [topics](https://telegram.org/blog/topics-in-groups-collectible-usernames#topics-in-groups)
     * enabled)
     */
    @get:JsonProperty("is_forum")
    @param:JsonProperty("is_forum")
    public val isForum: Boolean? = null,
)
