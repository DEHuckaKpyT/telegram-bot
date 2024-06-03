package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * Represents an issue with the selfie with a document. The error is considered resolved when the
 * file with the selfie changes.
 *
 * @see [PassportElementErrorSelfie]
 * (https://core.telegram.org/bots/api/#passportelementerrorselfie)
 *
 * @author KScript
 *
 * @param type The section of the user's Telegram Passport which has the issue, one of “passport”,
 * “driver_license”, “identity_card”, “internal_passport”
 * @param fileHash Base64-encoded hash of the file with the selfie
 * @param message Error message
 */
public data class PassportElementErrorSelfie(
    /**
     * The section of the user's Telegram Passport which has the issue, one of “passport”,
     * “driver_license”, “identity_card”, “internal_passport”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Base64-encoded hash of the file with the selfie
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
    override val source: String = "selfie"
}
