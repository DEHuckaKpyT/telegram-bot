package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.factory.input.input
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import io.github.dehuckakpyt.telegrambot.model.telegram.InputMediaDocument
import io.github.dehuckakpyt.telegrambot.model.telegram.InputMediaPhoto
import io.github.dehuckakpyt.telegrambot.model.telegram.InputMediaVideo
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
        val message = sendPhoto(input("/files/cat.jpg"))
        val fileId: String = message.photo!![0].fileId

        sendPhoto(fileId, "So cute!")
    }

    command("/audio") {
        sendAudio(input("/files/Imagine Dragons - Enemy.mp3"))
    }

    command("/document") {
        sendDocument(
            document = input("named file.docx", "/files/test doc.docx"),
            thumbnail = input("/files/thumb.png")
        )
    }

    command("/video") {
        val file = File("example-spring/src/main/resources/files/wow.mp4")
        sendVideo(input(file))
    }

    command("/photos") {
        sendMediaGroup(listOf(
            InputMediaPhoto(media = input("cat123.jpg", "/files/cat.jpg")),
            InputMediaVideo(media = input("cat123.gif", "/files/cat.gif")),
        ))
    }

    command("/documents") {
        sendMediaGroup(listOf(
            InputMediaDocument(media = input("cat123.jpg", "/files/cat.jpg")),
            InputMediaDocument(media = input("cat111.gif", "/files/cat.gif")),
            InputMediaDocument(media = input("named file.docx", "/files/test doc.docx")),
            InputMediaDocument(media = input("named video.mp4", "/files/wow.mp4")),
        ))
    }
})