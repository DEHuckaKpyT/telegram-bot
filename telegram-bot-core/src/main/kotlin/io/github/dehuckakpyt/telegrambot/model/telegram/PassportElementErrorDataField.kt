package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents an issue in one of the data fields that was provided by the user. The error is
 * considered resolved when the field's value changes.
 *
 * @see [PassportElementErrorDataField]
 * (https://core.telegram.org/bots/api/#passportelementerrordatafield)
 *
 * @author KScript
 *
 * @param type The section of the user's Telegram Passport which has the error, one of
 * “personal_details”, “passport”, “driver_license”, “identity_card”, “internal_passport”, “address”
 * @param fieldName Name of the data field which has the error
 * @param dataHash Base64-encoded data hash
 * @param message Error message
 */
public data class PassportElementErrorDataField(
    /**
     * The section of the user's Telegram Passport which has the error, one of “personal_details”,
     * “passport”, “driver_license”, “identity_card”, “internal_passport”, “address”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Name of the data field which has the error
     */
    @get:JsonProperty("field_name")
    @param:JsonProperty("field_name")
    public val fieldName: String,
    /**
     * Base64-encoded data hash
     */
    @get:JsonProperty("data_hash")
    @param:JsonProperty("data_hash")
    public val dataHash: String,
    /**
     * Error message
     */
    @get:JsonProperty("message")
    @param:JsonProperty("message")
    override val message: String,
) : PassportElementError {
    @get:JsonProperty("source")
    override val source: String = "data"
}
