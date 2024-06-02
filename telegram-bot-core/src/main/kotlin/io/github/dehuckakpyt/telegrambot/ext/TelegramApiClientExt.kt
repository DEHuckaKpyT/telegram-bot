package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.client.TelegramApiClient
import io.github.dehuckakpyt.telegrambot.model.telegram.input.ContentInput
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.input.NamedContentInput
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
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

fun FormBuilder.appendThumbnailIfNotNull(key: String, value: ContentInput?) {
    value ?: return
    append("attach://$key", value.byteArray, headersOf(HttpHeaders.ContentDisposition, "filename=\"${value.name}\""))
}

fun FormBuilder.appendContentIfNotNull(key: String, value: ContentInput?) {
    value ?: return
    append(key, value.byteArray, headersOf(HttpHeaders.ContentDisposition, "filename=\"${value.name}\""))
}

fun FormBuilder.appendContentIfNotNull(value: NamedContentInput?) {
    value ?: return
    append(value.name, value.byteArray, headersOf(HttpHeaders.ContentDisposition, "filename=\"${value.name}\""))
}

fun FormBuilder.appendContentIfNotNull(value: Input?) {
    if (value == null) return
    appendContent(value)
}

fun FormBuilder.appendContent(value: Input) {
    if (value !is ContentInput) return
    appendContent(value.name, value)
}

fun FormBuilder.appendContentIfNotNull(key: String, value: Input?): Unit = when (value) {
    null -> Unit
    is ContentInput -> appendContent(key, value)
    is StringInput -> append(key, value.fileId)
}

fun FormBuilder.appendContent(key: String, value: Input): Unit = when (value) {
    is ContentInput -> appendContent(key, value)
    is StringInput -> append(key, value.fileId)
}

fun FormBuilder.appendContent(key: String, value: ContentInput) {
    append(key, value.byteArray, headersOf(HttpHeaders.ContentDisposition, "filename=\"${value.name}\""))
}
