package io.github.dehuckakpyt.telegrambot.auth

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

/**
 * @author Denis Matytsin
 */
class AdminApiAuthWebFilter(
    private val tokenStore: TelegramAdminApiTokenStore,
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val header = exchange.request.headers.getFirst("Authorization")

        if (header == null || !header.startsWith("Bearer ")) {
            return chain.filter(exchange)
        }

        val token = header.substring(7)
        val telegramUserId = tokenStore.resolve(token)

        if (telegramUserId == null) {
            return chain.filter(exchange)
        }

        val authentication = UsernamePasswordAuthenticationToken(
            TelegramAdminApiPrincipal(telegramUserId),
            null,
            listOf(SimpleGrantedAuthority("ADMIN"))
        )

        val context = SecurityContextImpl(authentication)

        return chain.filter(exchange)
            .contextWrite(
                ReactiveSecurityContextHolder.withSecurityContext(
                    Mono.just(context)
                )
            )
    }
}