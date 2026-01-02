package io.github.dehuckakpyt.telegrambot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource


/**
 * @author Denis Matytsin
 */
@Configuration
@EnableWebFluxSecurity
class AdminUiSecurityConfig(
    @param:Value("\${TELEGRAM_BOT_ADMIN_UI_USERNAME:admin}") private val adminUsername: String,
    @param:Value("\${TELEGRAM_BOT_ADMIN_UI_PASSWORD:11}") private val adminPassword: String,
) {

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val user = User.withUsername(adminUsername)
            .password("{noop}$adminPassword") // no-op encoder
            .roles("ADMIN")
            .build()

        return MapReactiveUserDetailsService(user)
    }

    @Bean
    fun securityChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeExchange {
                it.pathMatchers("/**").permitAll()
                it.anyExchange().permitAll()
            }
            .httpBasic { }
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