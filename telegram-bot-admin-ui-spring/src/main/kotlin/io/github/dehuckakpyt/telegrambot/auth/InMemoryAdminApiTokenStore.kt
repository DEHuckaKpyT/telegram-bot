package io.github.dehuckakpyt.telegrambot.auth

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Duration
import kotlin.time.TimeMark
import kotlin.time.TimeSource


/**
 * @author Denis Matytsin
 */
class InMemoryAdminApiTokenStore(
    private val ttl: Duration,
    private val timeSource: TimeSource = TimeSource.Monotonic,
) : TelegramAdminApiTokenStore {

    private val tokens = ConcurrentHashMap<String, UserEntry>()

    override fun issue(telegramUserId: Long): String {
        val token = UUID.randomUUID().toString()

        val expiresAt = timeSource.markNow().plus(ttl)

        tokens[token] = UserEntry(
            telegramUserId = telegramUserId,
            expiresAt = expiresAt
        )

        return token
    }

    override fun resolve(token: String): Long? {
        val entry = tokens[token] ?: return null

        if (entry.expiresAt.hasPassedNow()) {
            tokens.remove(token)
            return null
        }

        entry.expiresAt = timeSource.markNow().plus(ttl)

        return entry.telegramUserId
    }

    override fun revoke(token: String) {
        tokens.remove(token)
    }

    private data class UserEntry(
        val telegramUserId: Long,
        var expiresAt: TimeMark,
    )
}