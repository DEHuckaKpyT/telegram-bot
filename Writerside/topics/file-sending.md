# File sending

Sending files is easy.

```kotlin
fun BotHandling.sendFilesHandler() {

    command("/audio") {
        // you can specify a path
        sendAudio(content("/files/Imagine Dragons - Enemy.mp3"))
    }

    command("/video") {
        // you can send a java.io.File
        val file = File("src/main/resources/files/wow.mp4")
        sendVideo(content(file))
    }
    
    command("/photo") {
        val message = sendPhoto(content("/files/cat.jpg"))
        val fileId: String = message.photo[0].fileId

        // you can specify a fileId
        sendPhoto(fileId, "So cute!")
    }

    command("/document") {
        sendDocument(
            // and you can specify a name of sending file
            document = content("named file.docx", "/files/test doc.docx"),
            thumbnail = content("/files/thumb.png")
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
}
```