package io.github.dehuckakpyt.telegrambot.manager.access.admin

import org.jetbrains.annotations.ApiStatus.Experimental
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authorization.AuthorizationContext

/**
 * Manager for access to admin-ui api.
 *
 * @author Denis Matytsin
 */
@Experimental
interface AdminApiAccessManager {

    /**
     * Returns True when has access to api.
     *
     * @param apiName name of the api (for example "GET /admin/telegram-users", "GET /admin/telegram-messages")
     */
    suspend fun hasAccess(apiName: String, authentication: Authentication, context: AuthorizationContext): Boolean {
        return authentication.authorities.any { authority -> authority.authority == "ADMIN" }
    }
}
