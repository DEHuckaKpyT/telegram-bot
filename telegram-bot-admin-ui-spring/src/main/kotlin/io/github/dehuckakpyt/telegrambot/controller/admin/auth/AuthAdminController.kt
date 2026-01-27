package io.github.dehuckakpyt.telegrambot.controller.admin.auth

import com.google.common.base.CaseFormat.LOWER_CAMEL
import com.google.common.base.CaseFormat.LOWER_UNDERSCORE
import io.github.dehuckakpyt.telegrambot.auth.TelegramAdminApiTokenStore
import io.github.dehuckakpyt.telegrambot.util.auth.TelegramAuthChecker
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.web.bind.annotation.*
import java.time.Instant


/**
 * @author Denis Matytsin
 */
@RestController
@RequestMapping("\${telegram-bot.administration.admin-api.prefix:/api}/admin/auth")
class AuthAdminController(
    private val tokenStore: TelegramAdminApiTokenStore,
    private val telegramAuthChecker: TelegramAuthChecker,

    @Value("\${telegram-bot.administration.access.user-ids}") adminIds: String,
) {
    private val adminIds = adminIds.split(',').map(String::toLong)

    @PostMapping("/login")
    suspend fun login(@RequestBody data: Map<String, String>): Map<String, Any> {
        if (!telegramAuthChecker.isValid(data)) {
            throw BadCredentialsException("Invalid Telegram signature.")
        }

        val authDate = data["auth_date"]!!.toLong()
        val now = Instant.now().epochSecond
        if (now - authDate > 86400) {
            throw CredentialsExpiredException("Authentication is expired.")
        }

        val telegramUserId = data["id"]!!.toLong()
        if (telegramUserId !in adminIds) {
            throw AccessDeniedException("You are not admin.")
        }
        val token = tokenStore.issue(telegramUserId)

        return mapOf(
            "token" to token,
            "telegramUserData" to data.mapKeys { LOWER_UNDERSCORE.to(LOWER_CAMEL, it.key) }
        )
    }

    @PostMapping("/logout")
    suspend fun logout(@RequestHeader("Authorization") bearerToken: String): Unit {
        if (!bearerToken.startsWith("Bearer ")) return
        val tokenValue = bearerToken.substring(7)
        tokenStore.revoke(tokenValue)
    }
}
