package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.BotHandler
import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.model.type.InputMediaDocument
import io.github.dehuckakpyt.telegrambot.model.type.InputMediaPhoto
import io.github.dehuckakpyt.telegrambot.model.type.InputMediaVideo
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.ContentFactory.content
import java.io.File


/**
 * Created on 26.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
@HandlerComponent
class SendFilesHandler : BotHandler({
    command("/photo") {
        val message = sendPhoto(content("/files/cat.jpg"))
        val fileId: String = message.photo[0].fileId

        sendPhoto(fileId, "So cute!")
    }

    command("/audio") {
        sendAudio(content("/files/Imagine Dragons - Enemy.mp3"))
    }

    command("/document") {
        sendDocument(
            document = content("named file.docx", "/files/test doc.docx"),
            thumbnail = content("/files/thumb.png")
        )
    }

    command("/video") {
        val file = File("example-spring/src/main/resources/files/wow.mp4")
        sendVideo(content(file))
    }

    command("/photos") {
        sendMediaGroup(
            listOf(
                InputMediaPhoto(media = content("cat123.jpg", "/files/cat.jpg")),
                InputMediaVideo(media = content("cat123.gif", "/files/cat.gif")),
            )
        )
    }

    command("/documents") {
        sendMediaGroup(
            listOf(
                InputMediaDocument(media = content("cat123.jpg", "/files/cat.jpg")),
                InputMediaDocument(media = content("cat111.gif", "/files/cat.gif")),
                InputMediaDocument(media = content("named file.docx", "/files/test doc.docx")),
                InputMediaDocument(media = content("named video.mp4", "/files/wow.mp4")),
            )
        )
    }
})