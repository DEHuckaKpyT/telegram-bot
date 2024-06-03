package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import io.github.dehuckakpyt.telegrambot.model.telegram.input.NamedResourceContent
import kotlinx.coroutines.delay
import org.koin.core.annotation.Factory


/**
 * Created on 08.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Factory
class SendPhotoHandler : BotHandler({
    command("/photo") {
        val message = sendPhoto(NamedResourceContent("Hello.jpg", "/images/cat.jpg"))
        val fileId = message.photo!![0].fileId

        delay(500)
        sendPhoto(fileId, "So cute!")
    }
})