package io.github.dehuckakpyt.telegrambot.api.util

import io.ktor.client.statement.*


/**
 * Created on 19.04.2024.
 *
 * @author Denis Matytsin
 */
interface TelegramUtilApi {
    suspend fun download(filePath: String): HttpResponse
}