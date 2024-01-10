package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.BotHandler
import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.model.type.InputMediaPhoto
import io.github.dehuckakpyt.telegrambot.model.type.InputMediaVideo
import io.github.dehuckakpyt.telegrambot.model.type.supplement.NamedResourceContent
import kotlinx.coroutines.delay


/**
 * Created on 08.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@HandlerComponent
class SendPhotosHandler : BotHandler({
    command("/photos") {
        val message = sendPhoto(NamedResourceContent("Hello.jpg", "/images/cat.jpg"))
        val fileId = message.photo[0].fileId

        delay(500)
        sendPhoto(fileId, "So cute!")
    }

    command("/photos_group") {
        sendMediaGroup(
            listOf(
                InputMediaPhoto(media = NamedResourceContent("cat123.jpg", "/images/cat.jpg")),
                InputMediaVideo(media = NamedResourceContent("cat123.gif", "/images/cat.gif")),
            )
        )
    }
})