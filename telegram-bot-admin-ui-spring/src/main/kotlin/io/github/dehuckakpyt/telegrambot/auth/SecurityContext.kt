package io.github.dehuckakpyt.telegrambot.auth

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.jetbrains.annotations.ApiStatus.Experimental
import org.springframework.security.core.context.ReactiveSecurityContextHolder


/**
 * @author Denis Matytsin
 */
@Experimental
suspend fun currentUserPrincipal(): TelegramAdminUIPrincipal? {
    val context = ReactiveSecurityContextHolder.getContext().awaitSingleOrNull() ?: return null

    val authentication = context.authentication ?: return null

    return authentication.principal as? TelegramAdminUIPrincipal
}

@Experimental
suspend fun currentUserRoles(): Set<String>? {
    val context = ReactiveSecurityContextHolder.getContext().awaitSingleOrNull() ?: return null

    val authentication = context.authentication ?: return null

    return authentication.authorities?.mapTo(mutableSetOf()) { it.authority }
}