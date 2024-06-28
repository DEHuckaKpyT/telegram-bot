package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * Represents an issue with the translated version of a document. The error is considered resolved
 * when a file with the document translation change.
 *
 * @see [PassportElementErrorTranslationFiles]
 * (https://core.telegram.org/bots/api/#passportelementerrortranslationfiles)
 *
 * @author KScript
 *
 * @param type Type of element of the user's Telegram Passport which has the issue, one of
 * “passport”, “driver_license”, “identity_card”, “internal_passport”, “utility_bill”,
 * “bank_statement”, “rental_agreement”, “passport_registration”, “temporary_registration”
 * @param fileHashes List of base64-encoded file hashes
 * @param message Error message
 */
public data class PassportElementErrorTranslationFiles(
    /**
     * Type of element of the user's Telegram Passport which has the issue, one of “passport”,
     * “driver_license”, “identity_card”, “internal_passport”, “utility_bill”, “bank_statement”,
     * “rental_agreement”, “passport_registration”, “temporary_registration”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * List of base64-encoded file hashes
     */
    @get:JsonProperty("file_hashes")
    @param:JsonProperty("file_hashes")
    public val fileHashes: List<String>,
    /**
     * Error message
     */
    @get:JsonProperty("message")
    @param:JsonProperty("message")
    override val message: String,
) : PassportElementError {
    @get:JsonProperty("source")
    override val source: String = "translation_files"
}
