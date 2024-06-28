package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * Describes documents or other Telegram Passport elements shared with the bot by the user.
 *
 * @see [EncryptedPassportElement] (https://core.telegram.org/bots/api/#encryptedpassportelement)
 *
 * @author KScript
 *
 * @param type Element type. One of “personal_details”, “passport”, “driver_license”,
 * “identity_card”, “internal_passport”, “address”, “utility_bill”, “bank_statement”,
 * “rental_agreement”, “passport_registration”, “temporary_registration”, “phone_number”, “email”.
 * @param data *Optional*. Base64-encoded encrypted Telegram Passport element data provided by the
 * user; available only for “personal_details”, “passport”, “driver_license”, “identity_card”,
 * “internal_passport” and “address” types. Can be decrypted and verified using the accompanying
 * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
 * @param phoneNumber *Optional*. User's verified phone number; available only for “phone_number”
 * type
 * @param email *Optional*. User's verified email address; available only for “email” type
 * @param files *Optional*. Array of encrypted files with documents provided by the user; available
 * only for “utility_bill”, “bank_statement”, “rental_agreement”, “passport_registration” and
 * “temporary_registration” types. Files can be decrypted and verified using the accompanying
 * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
 * @param frontSide *Optional*. Encrypted file with the front side of the document, provided by the
 * user; available only for “passport”, “driver_license”, “identity_card” and “internal_passport”. The
 * file can be decrypted and verified using the accompanying
 * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
 * @param reverseSide *Optional*. Encrypted file with the reverse side of the document, provided by
 * the user; available only for “driver_license” and “identity_card”. The file can be decrypted and
 * verified using the accompanying
 * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
 * @param selfie *Optional*. Encrypted file with the selfie of the user holding a document, provided
 * by the user; available if requested for “passport”, “driver_license”, “identity_card” and
 * “internal_passport”. The file can be decrypted and verified using the accompanying
 * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
 * @param translation *Optional*. Array of encrypted files with translated versions of documents
 * provided by the user; available if requested for “passport”, “driver_license”, “identity_card”,
 * “internal_passport”, “utility_bill”, “bank_statement”, “rental_agreement”, “passport_registration”
 * and “temporary_registration” types. Files can be decrypted and verified using the accompanying
 * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
 * @param hash Base64-encoded element hash for using in
 * [PassportElementErrorUnspecified](https://core.telegram.org/bots/api/#passportelementerrorunspecified)
 */
public data class EncryptedPassportElement(
    /**
     * Element type. One of “personal_details”, “passport”, “driver_license”, “identity_card”,
     * “internal_passport”, “address”, “utility_bill”, “bank_statement”, “rental_agreement”,
     * “passport_registration”, “temporary_registration”, “phone_number”, “email”.
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: String,
    /**
     * *Optional*. Base64-encoded encrypted Telegram Passport element data provided by the user;
     * available only for “personal_details”, “passport”, “driver_license”, “identity_card”,
     * “internal_passport” and “address” types. Can be decrypted and verified using the accompanying
     * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
     */
    @get:JsonProperty("data")
    @param:JsonProperty("data")
    public val `data`: String? = null,
    /**
     * *Optional*. User's verified phone number; available only for “phone_number” type
     */
    @get:JsonProperty("phone_number")
    @param:JsonProperty("phone_number")
    public val phoneNumber: String? = null,
    /**
     * *Optional*. User's verified email address; available only for “email” type
     */
    @get:JsonProperty("email")
    @param:JsonProperty("email")
    public val email: String? = null,
    /**
     * *Optional*. Array of encrypted files with documents provided by the user; available only for
     * “utility_bill”, “bank_statement”, “rental_agreement”, “passport_registration” and
     * “temporary_registration” types. Files can be decrypted and verified using the accompanying
     * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
     */
    @get:JsonProperty("files")
    @param:JsonProperty("files")
    public val files: List<PassportFile>? = null,
    /**
     * *Optional*. Encrypted file with the front side of the document, provided by the user;
     * available only for “passport”, “driver_license”, “identity_card” and “internal_passport”. The
     * file can be decrypted and verified using the accompanying
     * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
     */
    @get:JsonProperty("front_side")
    @param:JsonProperty("front_side")
    public val frontSide: PassportFile? = null,
    /**
     * *Optional*. Encrypted file with the reverse side of the document, provided by the user;
     * available only for “driver_license” and “identity_card”. The file can be decrypted and verified
     * using the accompanying
     * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
     */
    @get:JsonProperty("reverse_side")
    @param:JsonProperty("reverse_side")
    public val reverseSide: PassportFile? = null,
    /**
     * *Optional*. Encrypted file with the selfie of the user holding a document, provided by the
     * user; available if requested for “passport”, “driver_license”, “identity_card” and
     * “internal_passport”. The file can be decrypted and verified using the accompanying
     * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
     */
    @get:JsonProperty("selfie")
    @param:JsonProperty("selfie")
    public val selfie: PassportFile? = null,
    /**
     * *Optional*. Array of encrypted files with translated versions of documents provided by the
     * user; available if requested for “passport”, “driver_license”, “identity_card”,
     * “internal_passport”, “utility_bill”, “bank_statement”, “rental_agreement”,
     * “passport_registration” and “temporary_registration” types. Files can be decrypted and verified
     * using the accompanying
     * [EncryptedCredentials](https://core.telegram.org/bots/api/#encryptedcredentials).
     */
    @get:JsonProperty("translation")
    @param:JsonProperty("translation")
    public val translation: List<PassportFile>? = null,
    /**
     * Base64-encoded element hash for using in
     * [PassportElementErrorUnspecified](https://core.telegram.org/bots/api/#passportelementerrorunspecified)
     */
    @get:JsonProperty("hash")
    @param:JsonProperty("hash")
    public val hash: String,
)
