package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes data required for decrypting and authenticating
 * [EncryptedPassportElement](https://core.telegram.org/bots/api/#encryptedpassportelement). See the
 * [Telegram Passport Documentation](https://core.telegram.org/passport#receiving-information) for a
 * complete description of the data decryption and authentication processes.
 *
 * @see [EncryptedCredentials] (https://core.telegram.org/bots/api/#encryptedcredentials)
 *
 * @author KScript
 *
 * @param data Base64-encoded encrypted JSON-serialized data with unique user's payload, data hashes
 * and secrets required for
 * [EncryptedPassportElement](https://core.telegram.org/bots/api/#encryptedpassportelement) decryption
 * and authentication
 * @param hash Base64-encoded data hash for data authentication
 * @param secret Base64-encoded secret, encrypted with the bot's public RSA key, required for data
 * decryption
 */
public data class EncryptedCredentials(
    /**
     * Base64-encoded encrypted JSON-serialized data with unique user's payload, data hashes and
     * secrets required for
     * [EncryptedPassportElement](https://core.telegram.org/bots/api/#encryptedpassportelement)
     * decryption and authentication
     */
    @get:JsonProperty("data")
    @param:JsonProperty("data")
    public val `data`: String,
    /**
     * Base64-encoded data hash for data authentication
     */
    @get:JsonProperty("hash")
    @param:JsonProperty("hash")
    public val hash: String,
    /**
     * Base64-encoded secret, encrypted with the bot's public RSA key, required for data decryption
     */
    @get:JsonProperty("secret")
    @param:JsonProperty("secret")
    public val secret: String,
)
