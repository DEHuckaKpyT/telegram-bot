package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.auth.AdminApiAccessAuthorizationManager
import io.github.dehuckakpyt.telegrambot.auth.AdminApiAuthWebFilter
import io.github.dehuckakpyt.telegrambot.auth.TelegramAdminApiTokenStore
import io.github.dehuckakpyt.telegrambot.manager.access.admin.AdminApiAccessManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import reactor.core.publisher.Mono


/**
 * @author Denis Matytsin
 */
@Configuration
@EnableWebFluxSecurity
class AdminApiSecurityConfig(
    private val tokenStore: TelegramAdminApiTokenStore,
    private val adminApiAccessManager: AdminApiAccessManager,
    @param:Value("\${telegram-bot.administration.admin-api.prefix:/api}") private val apiPrefix: String,
    @param:Value("\${telegram-bot.administration.admin-panel.prefix:/admin-ui}") private val adminPanelPrefix: String,
) {

    @Bean
    fun securityChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .exceptionHandling {
                it.authenticationEntryPoint { swe, e ->
                    Mono.fromRunnable { swe.response.statusCode = HttpStatus.UNAUTHORIZED }
                }
                it.accessDeniedHandler { swe, e ->
                    Mono.fromRunnable { swe.response.statusCode = HttpStatus.FORBIDDEN }
                }
            }
            .authorizeExchange {
                it.pathMatchers("/vite.svg").permitAll()
                it.pathMatchers("/assets/**").permitAll()
                it.pathMatchers("/admin-panel/config").permitAll()
                it.pathMatchers("$adminPanelPrefix/**").permitAll()
                it.pathMatchers("$apiPrefix/admin/auth/login").permitAll()
                it.pathMatchers("$apiPrefix/admin/auth/logout").permitAll()
                it.pathMatchers(HttpMethod.GET, "$apiPrefix/admin/telegram-users/**")
                    .access(AdminApiAccessAuthorizationManager("GET /admin/telegram-users", adminApiAccessManager))
                it.pathMatchers(HttpMethod.GET, "$apiPrefix/admin/telegram-messages/**")
                    .access(AdminApiAccessAuthorizationManager("GET /admin/telegram-messages", adminApiAccessManager))
                it.anyExchange().denyAll()
            }
            .addFilterAt(
                AdminApiAuthWebFilter(tokenStore),
                SecurityWebFiltersOrder.AUTHENTICATION
            )
            .build()
    }

    @Bean
    fun corsWebFilter(): CorsWebFilter {
        val config = CorsConfiguration()
        config.allowedOrigins = listOf("*")
        config.allowedMethods = listOf("*")
        config.allowedHeaders = listOf("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)

        return CorsWebFilter(source)
    }
}