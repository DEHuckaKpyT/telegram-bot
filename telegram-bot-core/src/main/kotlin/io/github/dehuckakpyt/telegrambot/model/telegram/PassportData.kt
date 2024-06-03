package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * Describes Telegram Passport data shared with the bot by the user.
 *
 * @see [PassportData] (https://core.telegram.org/bots/api/#passportdata)
 *
 * @author KScript
 *
 * @param data Array with information about documents and other Telegram Passport elements that was
 * shared with the bot
 * @param credentials Encrypted credentials required to decrypt the data
 */
public data class PassportData(
    /**
     * Array with information about documents and other Telegram Passport elements that was shared
     * with the bot
     */
    @get:JsonProperty("data")
    @param:JsonProperty("data")
    public val `data`: List<EncryptedPassportElement>,
    /**
     * Encrypted credentials required to decrypt the data
     */
    @get:JsonProperty("credentials")
    @param:JsonProperty("credentials")
    public val credentials: EncryptedCredentials,
)
