package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public data class PassportData(
    @get:JsonProperty("data") @param:JsonProperty("data") val data: List<EncryptedPassportElement>,
    @get:JsonProperty("credentials") @param:JsonProperty("credentials") val credentials: EncryptedCredentials,
)

public data class PassportFile(
    @get:JsonProperty("file_id") @param:JsonProperty("file_id") val fileId: String,
    @get:JsonProperty("file_unique_id") @param:JsonProperty("file_unique_id") val fileUniqueId: String,
    @get:JsonProperty("file_size") @param:JsonProperty("file_size") val fileSize: Int,
    @get:JsonProperty("file_date") @param:JsonProperty("file_date") val fileDate: Long,
)

public data class EncryptedPassportElement(
    @get:JsonProperty("type") @param:JsonProperty("type") val type: ElementType,
    @get:JsonProperty("data") @param:JsonProperty("data") val data: String? = null,
    @get:JsonProperty("phone_number") @param:JsonProperty("phone_number") val phoneNumber: String? = null,
    @get:JsonProperty("email") @param:JsonProperty("email") val email: String?,
    @get:JsonProperty("files") @param:JsonProperty("files") val files: List<PassportFile>? = null,
    @get:JsonProperty("front_side") @param:JsonProperty("front_side") val frontSide: PassportFile? = null,
    @get:JsonProperty("reverse_side") @param:JsonProperty("reverse_side") val reverseSide: PassportFile? = null,
    @get:JsonProperty("selfie") @param:JsonProperty("selfie") val selfie: PassportFile? = null,
    @get:JsonProperty("translation") @param:JsonProperty("translation") val translation: List<PassportFile>? = null,
    @get:JsonProperty("hash") @param:JsonProperty("hash") val hash: String,
)

public data class EncryptedCredentials(
    @get:JsonProperty("data") @param:JsonProperty("data") val data: String,
    @get:JsonProperty("hash") @param:JsonProperty("hash") val hash: String,
    @get:JsonProperty("secret") @param:JsonProperty("secret") val secret: String,
)

public sealed class PassportElementError(
    @get:JsonProperty("source") public val source: String,
)

public data class PassportElementErrorDataField(
    @get:JsonProperty("type") public val type: String,
    @get:JsonProperty("message") public val message: String,
    @get:JsonProperty("field_name") public val fieldName: String,
    @get:JsonProperty("data_hash") public val dataHash: String,
) : PassportElementError(source = "data")

public data class PassportElementErrorFrontSide(
    @get:JsonProperty("type") public val type: String,
    @get:JsonProperty("message") public val message: String,
    @get:JsonProperty("file_hash") public val fileHash: String,
) : PassportElementError(source = "front_side")

public data class PassportElementErrorReverseSide(
    @get:JsonProperty("type") public val type: String,
    @get:JsonProperty("message") public val message: String,
    @get:JsonProperty("file_hash") public val fileHash: String,
) : PassportElementError(source = "reverse_side")

public data class PassportElementErrorSelfie(
    @get:JsonProperty("type") public val type: String,
    @get:JsonProperty("message") public val message: String,
    @get:JsonProperty("file_hash") public val fileHash: String,
) : PassportElementError(source = "selfie")

public data class PassportElementErrorFile(
    @get:JsonProperty("type") public val type: String,
    @get:JsonProperty("message") public val message: String,
    @get:JsonProperty("file_hash") public val fileHash: String,
) : PassportElementError(source = "file")

public data class PassportElementErrorFiles(
    @get:JsonProperty("type") public val type: String,
    @get:JsonProperty("message") public val message: String,
    @get:JsonProperty("file_hashes") public val fileHashes: List<String>,
) : PassportElementError(source = "files")

public data class PassportElementErrorTranslationFile(
    @get:JsonProperty("type") public val type: String,
    @get:JsonProperty("message") public val message: String,
    @get:JsonProperty("file_hash") public val fileHash: String,
) : PassportElementError(source = "translation_file")

public data class PassportElementErrorTranslationFiles(
    @get:JsonProperty("type") public val type: String,
    @get:JsonProperty("message") public val message: String,
    @get:JsonProperty("file_hashes") public val fileHashes: List<String>,
) : PassportElementError(source = "translation_files")

public data class PassportElementErrorUnspecified(
    @get:JsonProperty("type") public val type: String,
    @get:JsonProperty("message") public val message: String,
    @get:JsonProperty("element_hash") public val elementHash: String,
) : PassportElementError(source = "unspecified")

public enum class ElementType {
    @field:JsonProperty("personal_details")
    PERSONAL_DETAILS,

    @field:JsonProperty("passport")
    PASSPORT,

    @field:JsonProperty("driver_license")
    DRIVER_LICENSE,

    @field:JsonProperty("identity_card")
    IDENTITY_CARD,

    @field:JsonProperty("internal_passport")
    INTERNAL_PASSPORT,

    @field:JsonProperty("address")
    ADDRESS,

    @field:JsonProperty("utility_bill")
    UTILITY_BILL,

    @field:JsonProperty("bank_statement")
    BANK_STATEMENT,

    @field:JsonProperty("rental_agreement")
    RENTAL_AGREEMENT,

    @field:JsonProperty("passport_registration")
    PASSPORT_REGISTRATION,

    @field:JsonProperty("temporary_registration")
    TEMPORARY_REGISTRATION,

    @field:JsonProperty("phone_number")
    PHONE_NUMBER,

    @field:JsonProperty("email")
    EMAIL,
}