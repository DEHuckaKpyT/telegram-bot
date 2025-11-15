package io.github.dehuckakpyt.telegrambot.config.constants.api.client

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.addSerializer
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import io.github.dehuckakpyt.telegrambot.api.serializer.ContentInputSerializer
import io.github.dehuckakpyt.telegrambot.api.serializer.StringInputSerializer
import io.github.dehuckakpyt.telegrambot.model.telegram.input.ContentInput
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.URLProtocol.Companion.HTTPS
import io.ktor.serialization.jackson.*


/**
 * @author Denis Matytsin
 */
public object ApiConstants {
    public val DEFAULT_MAPPER = jacksonMapperBuilder().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }.build().apply {
        setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)

        registerModule(SimpleModule().apply {
            addSerializer(StringInput::class, StringInputSerializer())
            addSerializer(ContentInput::class, ContentInputSerializer())
        })

        registerModule(
            KotlinModule.Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .configure(KotlinFeature.StrictNullChecks, false)
                .build()
        )
    }

    public val DEFAULT_CLIENT_CONFIGURATION: HttpClientConfig<ApacheEngineConfig>.(String) -> Unit = { token ->
        install(ContentNegotiation) {
            register(Json, JacksonConverter(DEFAULT_MAPPER))
        }
        engine {
            socketTimeout = 600_000
        }
        defaultRequest {
            url {
                protocol = HTTPS
                host = "api.telegram.org"
                path("/bot$token/")
            }
        }
    }
}