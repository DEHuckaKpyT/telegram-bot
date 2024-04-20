package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.ktor.client.statement.*


/**
 * Created on 23.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
suspend fun TelegramBot.downloadById(fileId: String): HttpResponse {
    val fileInfo = getFile(fileId)
    val filePath = fileInfo.filePath ?: throw IllegalStateException("Failed download file. FilePath is null for file $fileInfo.")

    return download(filePath)
}
