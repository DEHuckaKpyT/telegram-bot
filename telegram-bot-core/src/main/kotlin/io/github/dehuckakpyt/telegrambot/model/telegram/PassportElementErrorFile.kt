package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * Represents an issue with a document scan. The error is considered resolved when the file with the
 * document scan changes.
 *
 * @see [PassportElementErrorFile] (https://core.telegram.org/bots/api/#passportelementerrorfile)
 *
 * @author KScript
 *
 * @param type The section of the user's Telegram Passport which has the issue, one of
 * “utility_bill”, “bank_statement”, “rental_agreement”, “passport_registration”,
 * “temporary_registration”
 * @param fileHash Base64-encoded file hash
 * @param message Error message
 */
public data class PassportElementErrorFile(
    /**
     * The section of the user's Telegram Passport which has the issue, one of “utility_bill”,
     * “bank_statement”, “rental_agreement”, “passport_registration”, “temporary_registration”
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
    override val source: String = "file"
}
