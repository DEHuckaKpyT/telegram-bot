package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long

/**
 * Created on 02.06.2024.
 *
 * Describes why a request was unsuccessful.
 *
 * @see [ResponseParameters] (https://core.telegram.org/bots/api/#responseparameters)
 *
 * @author KScript
 *
 * @param migrateToChatId *Optional*. The group has been migrated to a supergroup with the specified
 * identifier. This number may have more than 32 significant bits and some programming languages may
 * have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
 * signed 64-bit integer or double-precision float type are safe for storing this identifier.
 * @param retryAfter *Optional*. In case of exceeding flood control, the number of seconds left to
 * wait before the request can be repeated
 */
public data class ResponseParameters(
    /**
     * *Optional*. The group has been migrated to a supergroup with the specified identifier. This
     * number may have more than 32 significant bits and some programming languages may have
     * difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
     * signed 64-bit integer or double-precision float type are safe for storing this identifier.
     */
    @get:JsonProperty("migrate_to_chat_id")
    @param:JsonProperty("migrate_to_chat_id")
    public val migrateToChatId: Long? = null,
    /**
     * *Optional*. In case of exceeding flood control, the number of seconds left to wait before the
     * request can be repeated
     */
    @get:JsonProperty("retry_after")
    @param:JsonProperty("retry_after")
    public val retryAfter: Int? = null,
)
