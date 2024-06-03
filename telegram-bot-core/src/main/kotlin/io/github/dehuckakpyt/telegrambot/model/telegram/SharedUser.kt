package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * This object contains information about a user that was shared with the bot using a
 * [KeyboardButtonRequestUsers](https://core.telegram.org/bots/api/#keyboardbuttonrequestusers) button.
 *
 * @see [SharedUser] (https://core.telegram.org/bots/api/#shareduser)
 *
 * @author KScript
 *
 * @param userId Identifier of the shared user. This number may have more than 32 significant bits
 * and some programming languages may have difficulty/silent defects in interpreting it. But it has at
 * most 52 significant bits, so 64-bit integers or double-precision float types are safe for storing
 * these identifiers. The bot may not have access to the user and could be unable to use this
 * identifier, unless the user is already known to the bot by some other means.
 * @param firstName *Optional*. First name of the user, if the name was requested by the bot
 * @param lastName *Optional*. Last name of the user, if the name was requested by the bot
 * @param username *Optional*. Username of the user, if the username was requested by the bot
 * @param photo *Optional*. Available sizes of the chat photo, if the photo was requested by the bot
 */
public data class SharedUser(
    /**
     * Identifier of the shared user. This number may have more than 32 significant bits and some
     * programming languages may have difficulty/silent defects in interpreting it. But it has at most
     * 52 significant bits, so 64-bit integers or double-precision float types are safe for storing
     * these identifiers. The bot may not have access to the user and could be unable to use this
     * identifier, unless the user is already known to the bot by some other means.
     */
    @get:JsonProperty("user_id")
    @param:JsonProperty("user_id")
    public val userId: Long,
    /**
     * *Optional*. First name of the user, if the name was requested by the bot
     */
    @get:JsonProperty("first_name")
    @param:JsonProperty("first_name")
    public val firstName: String? = null,
    /**
     * *Optional*. Last name of the user, if the name was requested by the bot
     */
    @get:JsonProperty("last_name")
    @param:JsonProperty("last_name")
    public val lastName: String? = null,
    /**
     * *Optional*. Username of the user, if the username was requested by the bot
     */
    @get:JsonProperty("username")
    @param:JsonProperty("username")
    public val username: String? = null,
    /**
     * *Optional*. Available sizes of the chat photo, if the photo was requested by the bot
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: List<PhotoSize>? = null,
)
