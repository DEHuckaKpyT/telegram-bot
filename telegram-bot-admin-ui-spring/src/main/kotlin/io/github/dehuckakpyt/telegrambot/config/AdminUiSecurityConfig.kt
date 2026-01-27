package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.auth.AdminUIAccessAuthorizationManager
import io.github.dehuckakpyt.telegrambot.auth.AdminUIAuthWebFilter
import io.github.dehuckakpyt.telegrambot.auth.TelegramAdminUITokenStore
import io.github.dehuckakpyt.telegrambot.manager.access.admin.AdminApiAccessManager
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
class AdminUiSecurityConfig(
    private val tokenStore: TelegramAdminUITokenStore,
    private val adminApiAccessManager: AdminApiAccessManager,
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
                it.pathMatchers("/").permitAll()
                it.pathMatchers("/assets/**").permitAll()
                it.pathMatchers("/vite.svg").permitAll()
                it.pathMatchers("/login").permitAll()
                it.pathMatchers("/admin-ui/config").permitAll()
                it.pathMatchers("/admin/auth/login").permitAll()
                it.pathMatchers("/admin/auth/logout").authenticated()
                it.pathMatchers(HttpMethod.GET, "/admin/telegram-users/**")
                    .access(AdminUIAccessAuthorizationManager("GET /admin/telegram-users", adminApiAccessManager))

                it.pathMatchers(HttpMethod.GET, "/admin/telegram-messages/**")
                    .access(AdminUIAccessAuthorizationManager("GET /admin/telegram-messages", adminApiAccessManager))
                it.anyExchange().denyAll()
            }
            .addFilterAt(
                AdminUIAuthWebFilter(tokenStore),
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