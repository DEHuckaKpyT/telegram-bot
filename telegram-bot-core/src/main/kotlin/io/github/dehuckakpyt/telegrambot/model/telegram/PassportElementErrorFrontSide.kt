package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * Represents an issue with the front side of a document. The error is considered resolved when the
 * file with the front side of the document changes.
 *
 * @see [PassportElementErrorFrontSide]
 * (https://core.telegram.org/bots/api/#passportelementerrorfrontside)
 *
 * @author KScript
 *
 * @param type The section of the user's Telegram Passport which has the issue, one of “passport”,
 * “driver_license”, “identity_card”, “internal_passport”
 * @param fileHash Base64-encoded hash of the file with the front side of the document
 * @param message Error message
 */
public data class PassportElementErrorFrontSide(
    /**
     * The section of the user's Telegram Passport which has the issue, one of “passport”,
     * “driver_license”, “identity_card”, “internal_passport”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Base64-encoded hash of the file with the front side of the document
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
    override val source: String = "front_side"
}
