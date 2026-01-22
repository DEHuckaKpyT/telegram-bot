package io.github.dehuckakpyt.telegrambot.util.auth

import io.github.dehuckakpyt.telegrambot.model.telegram.User
import org.apache.commons.codec.binary.Hex
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


/**
 * @author Denis Matytsin
 */
class TelegramAuthChecker(
    private val telegramBotToken: String,
) {

    /**
     * @return [User.id] if validation passed
     */
    fun getValidatedId(data: Map<String, String>): Long? {
        if (isValid(data).not()) return null

        return data["id"]!!.toLong()
    }

    /**
     * Code copied from github and fixed convert from bytes to string.
     *
     * @return True if user valid
     *
     * @see <a href="https://github.com/pengrad/java-telegram-bot-api/commit/5eb1ec4e24bf7eb83874cfdb1163d38d3b6fccfd">Original</a>
     */
    fun isValid(data: Map<String, String>): Boolean {
        val receivedHash = data["hash"] ?: return false
        val dataCheckString = data
            .filterKeys { it != "hash" }
            .toSortedMap()
            .entries
            .joinToString("\n") { "${it.key}=${it.value}" }

        return isValid(dataCheckString, receivedHash)
    }

    /**
     * Code copied from github and fixed convert from bytes to string.
     *
     * @return True if user valid
     *
     * @see <a href="https://github.com/pengrad/java-telegram-bot-api/commit/5eb1ec4e24bf7eb83874cfdb1163d38d3b6fccfd">Original</a>
     */
    fun isValid(authData: String, hash: String?): Boolean {
        val secret = sha256(telegramBotToken.toByteArray())
        val result = hmacSha256(secret, authData)

        return result == hash
    }

    private fun sha256(string: ByteArray?): ByteArray {
        val md = MessageDigest.getInstance("SHA-256")
        return md.digest(string)
    }

    private fun hmacSha256(key: ByteArray, data: String): String {
        val hmacSha256 = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(key, "HmacSHA256")
        hmacSha256.init(secretKey)
        val result = hmacSha256.doFinal(data.toByteArray())
        return Hex.encodeHexString(result)
    }
}