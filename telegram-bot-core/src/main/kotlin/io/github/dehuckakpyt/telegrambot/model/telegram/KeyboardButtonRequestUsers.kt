package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long

/**
 * Created on 02.06.2024.
 *
 * This object defines the criteria used to request suitable users. Information about the selected
 * users will be shared with the bot when the corresponding button is pressed. [More about requesting
 * users ](https://core.telegram.org/bots/features#chat-and-user-selection)
 *
 * @see [KeyboardButtonRequestUsers]
 * (https://core.telegram.org/bots/api/#keyboardbuttonrequestusers)
 *
 * @author KScript
 *
 * @param requestId Signed 32-bit identifier of the request that will be received back in the
 * [UsersShared](https://core.telegram.org/bots/api/#usersshared) object. Must be unique within the
 * message
 * @param userIsBot *Optional*. Pass *True* to request bots, pass *False* to request regular users.
 * If not specified, no additional restrictions are applied.
 * @param userIsPremium *Optional*. Pass *True* to request premium users, pass *False* to request
 * non-premium users. If not specified, no additional restrictions are applied.
 * @param maxQuantity *Optional*. The maximum number of users to be selected; 1-10. Defaults to 1.
 * @param requestName *Optional*. Pass *True* to request the users' first and last names
 * @param requestUsername *Optional*. Pass *True* to request the users' usernames
 * @param requestPhoto *Optional*. Pass *True* to request the users' photos
 */
public data class KeyboardButtonRequestUsers(
    /**
     * Signed 32-bit identifier of the request that will be received back in the
     * [UsersShared](https://core.telegram.org/bots/api/#usersshared) object. Must be unique within the
     * message
     */
    @get:JsonProperty("request_id")
    @param:JsonProperty("request_id")
    public val requestId: Long,
    /**
     * *Optional*. Pass *True* to request bots, pass *False* to request regular users. If not
     * specified, no additional restrictions are applied.
     */
    @get:JsonProperty("user_is_bot")
    @param:JsonProperty("user_is_bot")
    public val userIsBot: Boolean? = null,
    /**
     * *Optional*. Pass *True* to request premium users, pass *False* to request non-premium users.
     * If not specified, no additional restrictions are applied.
     */
    @get:JsonProperty("user_is_premium")
    @param:JsonProperty("user_is_premium")
    public val userIsPremium: Boolean? = null,
    /**
     * *Optional*. The maximum number of users to be selected; 1-10. Defaults to 1.
     */
    @get:JsonProperty("max_quantity")
    @param:JsonProperty("max_quantity")
    public val maxQuantity: Int? = null,
    /**
     * *Optional*. Pass *True* to request the users' first and last names
     */
    @get:JsonProperty("request_name")
    @param:JsonProperty("request_name")
    public val requestName: Boolean? = null,
    /**
     * *Optional*. Pass *True* to request the users' usernames
     */
    @get:JsonProperty("request_username")
    @param:JsonProperty("request_username")
    public val requestUsername: Boolean? = null,
    /**
     * *Optional*. Pass *True* to request the users' photos
     */
    @get:JsonProperty("request_photo")
    @param:JsonProperty("request_photo")
    public val requestPhoto: Boolean? = null,
)
