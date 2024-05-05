package io.github.dehuckakpyt.telegrambot.client

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import io.github.dehuckakpyt.telegrambot.exception.api.TelegramBotApiException
import io.github.dehuckakpyt.telegrambot.ext.toJson
import io.github.dehuckakpyt.telegrambot.model.type.supplement.TelegramResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.jackson.*
import java.text.SimpleDateFormat

/**
 * Created on 19.04.2024.
 *
 * @author Denis Matytsin
 */
internal class TelegramApiClient(
    private val token: String,
) {

    val client = HttpClient(Apache) {
        install(ContentNegotiation) {
            register(Json, JacksonConverter(MAPPER))
        }
        engine {
            socketTimeout = 0
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.telegram.org"
                path("/bot$token/")
            }
        }
    }

    suspend inline fun <reified R : Any> get(method: String): R = handleRequest(client.get(method))

    suspend inline fun <reified R> get(method: String, block: HttpRequestBuilder.() -> Unit): R =
        handleRequest(client.get(method, block))

    suspend inline fun <reified R : Any> postMultiPart(method: String, noinline block: FormBuilder.() -> Unit): R =
        handleRequest(client.post(method) {
            setBody(MultiPartFormDataContent(formData(block)))
        })

    suspend inline fun <reified R : Any> postJson(method: String, body: Any): R {
        val response = client.post(method) {
            contentType(Json)
            setBody(body)
        }
        val telegramResponse = response.body<TelegramResponse<R>>()

        if (!telegramResponse.ok) throwException(response, telegramResponse, body)

        return telegramResponse.result!!
    }

    private suspend inline fun <reified R : Any> handleRequest(response: HttpResponse): R {
        val telegramResponse = response.body<TelegramResponse<R>>()

        if (!telegramResponse.ok) throwException(response, telegramResponse)

        return telegramResponse.result!!
    }

    private fun throwException(response: HttpResponse, telegramResponse: TelegramResponse<*>, body: Any? = null) {
        throw TelegramBotApiException(
            """Request to Telegram Error. 
        Request
        Method: ${response.request.method.value}
        Content-type: ${response.request.contentType()?.contentType}
        Url: ${response.request.url}
        Body:
        ${toJson(body)}
        
        Response
        Code: ${telegramResponse.errorCode}. 
        Description: ${telegramResponse.description}"""
        )
    }

    fun toJson(any: Any): String = MAPPER.writeValueAsString(any)

    fun close(): Unit = client.close()

    companion object {
        private val MAPPER = jacksonMapperBuilder().apply {
            configure(SerializationFeature.INDENT_OUTPUT, true)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }.build().apply {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            })
            dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

            registerModule(JavaTimeModule())

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
    }
}