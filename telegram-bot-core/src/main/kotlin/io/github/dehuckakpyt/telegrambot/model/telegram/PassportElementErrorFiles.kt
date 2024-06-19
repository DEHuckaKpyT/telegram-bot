package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * Represents an issue with a list of scans. The error is considered resolved when the list of files
 * containing the scans changes.
 *
 * @see [PassportElementErrorFiles] (https://core.telegram.org/bots/api/#passportelementerrorfiles)
 *
 * @author KScript
 *
 * @param type The section of the user's Telegram Passport which has the issue, one of
 * “utility_bill”, “bank_statement”, “rental_agreement”, “passport_registration”,
 * “temporary_registration”
 * @param fileHashes List of base64-encoded file hashes
 * @param message Error message
 */
public data class PassportElementErrorFiles(
    /**
     * The section of the user's Telegram Passport which has the issue, one of “utility_bill”,
     * “bank_statement”, “rental_agreement”, “passport_registration”, “temporary_registration”
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
    override val source: String = "files"
}
