package io.github.dehuckakpyt.telegrambot.api.client

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.config.constants.api.client.ApiConstants.DEFAULT_CLIENT_CONFIGURATION
import io.github.dehuckakpyt.telegrambot.config.constants.api.client.ApiConstants.DEFAULT_MAPPER
import io.github.dehuckakpyt.telegrambot.exception.api.TelegramBotApiException
import io.github.dehuckakpyt.telegrambot.ext.toJson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json

/**
 * Created on 19.04.2024.
 *
 * @author Denis Matytsin
 */
public class TelegramApiClient(
    private val token: String,
    clientConfiguration: (HttpClientConfig<ApacheEngineConfig>.() -> Unit)? = null,
) {
    val client = HttpClient(Apache) {
        DEFAULT_CLIENT_CONFIGURATION(token)
        clientConfiguration?.invoke(this)
    }

    private val hiddenToken: String = buildString {
        if (token.length < 12 || token.contains(':').not()) {
            append(token)
            return@buildString
        }

        val visibleFirstCharsCount = 4
        val visibleLastCharsCount = 8

        val hideAfterFirstCharsCount = token.substringBefore(":").length - visibleFirstCharsCount
        val hideBeforeLastCharsCount = token.substringAfter(":").length - visibleLastCharsCount

        append(token.substring(0, visibleFirstCharsCount))
        append("*".repeat(hideAfterFirstCharsCount))
        append(":")
        append("*".repeat(hideBeforeLastCharsCount))
        append(token.substring(token.length - visibleLastCharsCount))
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

    suspend inline fun <reified R : Any> handleRequest(response: HttpResponse): R {
        val telegramResponse = response.body<TelegramResponse<R>>()

        if (!telegramResponse.ok) throwException(response, telegramResponse)

        return telegramResponse.result!!
    }

    fun throwException(response: HttpResponse, telegramResponse: TelegramResponse<*>, body: Any? = null) {
        throw TelegramBotApiException(
            """
        Failed request to telegram:
        Request info
        Method: ${response.request.method.value}
        Content-type: ${response.request.contentType()?.contentType}
        Url: ${response.request.url.toString().replace(token, hiddenToken)}
        Body: ${toJson(body)}
        
        Response info
        Code: ${telegramResponse.errorCode}
        Description: ${telegramResponse.description}"""
        )
    }

    suspend fun getFileApi(path: String): HttpResponse {
        return client.get("https://api.telegram.org/file/bot$token/${path}")
    }

    fun toJson(any: Any): String = DEFAULT_MAPPER.writeValueAsString(any)

    fun close(): Unit = client.close()

    companion object {}

    data class TelegramResponse<T>(
        @param:JsonProperty("ok") val ok: Boolean,
        @param:JsonProperty("result") val result: T?,
        @param:JsonProperty("error_code") val errorCode: Int?,
        @param:JsonProperty("description") val description: String?,
    )
}