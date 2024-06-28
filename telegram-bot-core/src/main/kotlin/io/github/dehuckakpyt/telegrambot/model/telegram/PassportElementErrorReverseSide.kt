package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents an issue with the reverse side of a document. The error is considered resolved when
 * the file with reverse side of the document changes.
 *
 * @see [PassportElementErrorReverseSide]
 * (https://core.telegram.org/bots/api/#passportelementerrorreverseside)
 *
 * @author KScript
 *
 * @param type The section of the user's Telegram Passport which has the issue, one of
 * “driver_license”, “identity_card”
 * @param fileHash Base64-encoded hash of the file with the reverse side of the document
 * @param message Error message
 */
public data class PassportElementErrorReverseSide(
    /**
     * The section of the user's Telegram Passport which has the issue, one of “driver_license”,
     * “identity_card”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Base64-encoded hash of the file with the reverse side of the document
     */
    @get:JsonProperty("file_hash")
    @param:JsonProperty("file_hash")
    public val fileHash: String,
    /**
     * Error message
     */
    @get:JsonProperty("message")
    @param:JsonProperty("message")
    override val message: String,
) : PassportElementError {
    @get:JsonProperty("source")
    override val source: String = "reverse_side"
}
