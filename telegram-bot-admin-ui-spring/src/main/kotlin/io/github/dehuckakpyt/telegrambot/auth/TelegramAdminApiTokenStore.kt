package io.github.dehuckakpyt.telegrambot.auth


/**
 * Storage for telegram users (in admin-ui) bearer tokens.
 *
 * @author Denis Matytsin
 */
interface TelegramAdminApiTokenStore {

    /**
     * Issues a new bearer token for the given Telegram user.
     *
     * This method is typically invoked after successful Telegram
     * authentication and user authorization.
     *
     * @param telegramUserId Telegram user identifier
     *
     * @return newly generated opaque bearer token
     */
    fun issue(telegramUserId: Long): String

    /**
     * Resolves a bearer token to a Telegram user identifier.
     *
     * This method is used by the security layer on each authenticated request.
     *
     * @param token bearer token from the Authorization header
     *
     * @return Telegram user identifier, or null if the token is invalid
     */
    fun resolve(token: String): Long?

    /**
     * Revokes the specified bearer token.
     *
     * After revocation, the token MUST be treated as invalid
     * and must no longer authenticate requests.
     *
     * This method is typically used for explicit logout or
     * administrative invalidation.
     *
     * @param token bearer token to revoke
     */
    fun revoke(token: String)
}