package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.client.TelegramApiClient
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.Content
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.NamedContent
import io.ktor.client.request.forms.*
import io.ktor.http.*


/**
 * Created on 19.04.2024.
 *
 * @author Denis Matytsin
 */
internal fun TelegramApiClient.toJson(any: Any?): String? = any?.let(::toJson)

fun FormBuilder.appendIfNotNull(key: String, value: String?) {
    value ?: return
    append(key, value)
}

fun FormBuilder.appendIfNotNull(key: String, value: Int?) {
    value ?: return
    append(key, value)
}

fun FormBuilder.appendIfNotNull(key: String, value: Long?) {
    value ?: return
    append(key, value)
}

fun FormBuilder.appendIfNotNull(key: String, value: Boolean?) {
    value ?: return
    append(key, value.toString())
}

fun FormBuilder.appendThumbnailIfNotNull(key: String, value: Content?) {
    value ?: return
    append("attach://$key", value.byteArray, headersOf(HttpHeaders.ContentDisposition, "filename=\"${value.name}\""))
}

fun FormBuilder.appendContentIfNotNull(key: String, value: Content?) {
    value ?: return
    append(key, value.byteArray, headersOf(HttpHeaders.ContentDisposition, "filename=\"${value.name}\""))
}

fun FormBuilder.appendContentIfNotNull(value: NamedContent?) {
    value ?: return
    append(value.name, value.byteArray, headersOf(HttpHeaders.ContentDisposition, "filename=\"${value.name}\""))
}

fun FormBuilder.appendContent(key: String, value: Content) {
    append(key, value.byteArray, headersOf(HttpHeaders.ContentDisposition, "filename=\"${value.name}\""))
}
