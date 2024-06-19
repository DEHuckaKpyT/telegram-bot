package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents an issue in an unspecified place. The error is considered resolved when new data is
 * added.
 *
 * @see [PassportElementErrorUnspecified]
 * (https://core.telegram.org/bots/api/#passportelementerrorunspecified)
 *
 * @author KScript
 *
 * @param type Type of element of the user's Telegram Passport which has the issue
 * @param elementHash Base64-encoded element hash
 * @param message Error message
 */
public data class PassportElementErrorUnspecified(
    /**
     * Type of element of the user's Telegram Passport which has the issue
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Base64-encoded element hash
     */
    @get:JsonProperty("element_hash")
    @param:JsonProperty("element_hash")
    public val elementHash: String,
    /**
     * Error message
     */
    @get:JsonProperty("message")
    @param:JsonProperty("message")
    override val message: String,
) : PassportElementError {
    @get:JsonProperty("source")
    override val source: String = "unspecified"
}
