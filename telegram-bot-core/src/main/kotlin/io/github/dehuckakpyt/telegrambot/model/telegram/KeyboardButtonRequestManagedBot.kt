package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * This object defines the parameters for the creation of a managed bot. Information about the
 * created bot will be shared with the bot using the update *managed_bot* and a
 * [Message](https://core.telegram.org/bots/api/#message) with the field *managed_bot_created*.
 *
 * @see [KeyboardButtonRequestManagedBot]
 * (https://core.telegram.org/bots/api/#keyboardbuttonrequestmanagedbot)
 *
 * @author KScript
 *
 * @param requestId Signed 32-bit identifier of the request. Must be unique within the message
 * @param suggestedName *Optional*. Suggested name for the bot
 * @param suggestedUsername *Optional*. Suggested username for the bot
 */
public data class KeyboardButtonRequestManagedBot(
    /**
     * Signed 32-bit identifier of the request. Must be unique within the message
     */
    @get:JsonProperty("request_id")
    @param:JsonProperty("request_id")
    public val requestId: Long,
    /**
     * *Optional*. Suggested name for the bot
     */
    @get:JsonProperty("suggested_name")
    @param:JsonProperty("suggested_name")
    public val suggestedName: String? = null,
    /**
     * *Optional*. Suggested username for the bot
     */
    @get:JsonProperty("suggested_username")
    @param:JsonProperty("suggested_username")
    public val suggestedUsername: String? = null,
)
