package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * Represents an issue with one of the files that constitute the translation of a document. The
 * error is considered resolved when the file changes.
 *
 * @see [PassportElementErrorTranslationFile]
 * (https://core.telegram.org/bots/api/#passportelementerrortranslationfile)
 *
 * @author KScript
 *
 * @param type Type of element of the user's Telegram Passport which has the issue, one of
 * “passport”, “driver_license”, “identity_card”, “internal_passport”, “utility_bill”,
 * “bank_statement”, “rental_agreement”, “passport_registration”, “temporary_registration”
 * @param fileHash Base64-encoded file hash
 * @param message Error message
 */
public data class PassportElementErrorTranslationFile(
    /**
     * Type of element of the user's Telegram Passport which has the issue, one of “passport”,
     * “driver_license”, “identity_card”, “internal_passport”, “utility_bill”, “bank_statement”,
     * “rental_agreement”, “passport_registration”, “temporary_registration”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Base64-encoded file hash
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
    override val source: String = "translation_file"
}
