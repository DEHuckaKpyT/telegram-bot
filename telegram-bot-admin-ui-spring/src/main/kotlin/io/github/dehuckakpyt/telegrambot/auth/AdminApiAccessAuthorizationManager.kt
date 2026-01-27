package io.github.dehuckakpyt.telegrambot.auth

import io.github.dehuckakpyt.telegrambot.manager.access.admin.AdminApiAccessManager
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationResult
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authorization.AuthorizationContext
import reactor.core.publisher.Mono


/**
 * @author Denis Matytsin
 */
class AdminApiAccessAuthorizationManager(
    private val apiName: String,

    private val adminApiAccessManager: AdminApiAccessManager,
) : ReactiveAuthorizationManager<AuthorizationContext> {

    @Deprecated("Deprecated in Java")
    override fun check(
        authentication: Mono<Authentication>,
        context: AuthorizationContext,
    ): Mono<AuthorizationDecision> = authentication.flatMap { auth ->
        mono {
            adminApiAccessManager.hasAccess(apiName, auth, context)
                .let(::AuthorizationDecision)
        }
    }

    override fun authorize(
        authentication: Mono<Authentication>,
        context: AuthorizationContext,
    ): Mono<AuthorizationResult> = authentication.flatMap { auth ->
        mono {
            adminApiAccessManager.hasAccess(apiName, auth, context)
                .let(::AuthorizationDecision)
        }
    }
}