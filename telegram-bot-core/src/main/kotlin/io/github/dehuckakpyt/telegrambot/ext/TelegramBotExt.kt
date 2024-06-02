package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.exception.api.TelegramBotApiException
import io.ktor.client.statement.*


/**
 * Created on 23.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
suspend fun TelegramBot.downloadByPath(filePath: String): HttpResponse {
    return client.getFileApi(filePath)
}

suspend fun TelegramBot.downloadById(fileId: String): HttpResponse {
    val fileInfo = getFile(fileId)
    val filePath = fileInfo.filePath ?: throw TelegramBotApiException("Failed download file. FilePath is null for file $fileInfo.")

    return downloadByPath(filePath)
}
