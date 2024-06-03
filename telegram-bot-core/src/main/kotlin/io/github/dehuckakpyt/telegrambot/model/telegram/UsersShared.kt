package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * This object contains information about the users whose identifiers were shared with the bot using
 * a [KeyboardButtonRequestUsers](https://core.telegram.org/bots/api/#keyboardbuttonrequestusers)
 * button.
 *
 * @see [UsersShared] (https://core.telegram.org/bots/api/#usersshared)
 *
 * @author KScript
 *
 * @param requestId Identifier of the request
 * @param users Information about users shared with the bot.
 */
public data class UsersShared(
    /**
     * Identifier of the request
     */
    @get:JsonProperty("request_id")
    @param:JsonProperty("request_id")
    public val requestId: Long,
    /**
     * Information about users shared with the bot.
     */
    @get:JsonProperty("users")
    @param:JsonProperty("users")
    public val users: List<SharedUser>,
)
