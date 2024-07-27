# File sending

Sending files is easy.

## Inside handlers

```kotlin
fun BotHandling.sendFilesHandler() {

    command("/audio") {
        // you can specify a path
        sendAudio(input("/files/Imagine Dragons - Enemy.mp3"))
    }

    command("/video") {
        // you can send a java.io.File
        val file = File("src/main/resources/files/wow.mp4")
        sendVideo(input(file))
    }
    
    command("/photo") {
        val message = sendPhoto(input("/files/cat.jpg"))
        val fileId: String = message.photo!![0].fileId

        // you can specify a fileId
        sendPhoto(fileId, caption = "So cute!")
    }

    command("/photos") {
        sendMediaGroup(listOf(
            // also in InputMedia you can use fileId as String and file as File or path
            InputMediaPhoto(media = "AgACAgIAAxkDAAIiH2Zdyo_8haA51WiPm24nUe08eVwBAAJ22jEbfvjxSqkxOQjpDDJWAQADAgADcwADNQQ"),
            InputMediaPhoto(media = input("cat123.jpg", "/files/cat.jpg")),
        ))
    }

    command("/document") {
        sendDocument(
            // and you can specify a name of sending file
            document = input("named file.docx", "/files/test doc.docx"),
            thumbnail = input("/files/thumb.png")
        )
    }

    command("/documents") {
        sendMediaGroup(listOf(
            InputMediaDocument(media = input("cat123.jpg", "/files/cat.jpg")),
            InputMediaDocument(media = input("cat111.gif", "/files/cat.gif")),
            InputMediaDocument(media = input("named file.docx", "/files/test doc.docx")),
            InputMediaDocument(media = input("named video.mp4", "/files/wow.mp4")),
        ))
    }
}
```

## In other classes

```kotlin
class PresentationTestClass(
    private val bot: TelegramBot,
    private val inputFactory: InputFactory,
) {
    val chatId = 123L
    bot.sendAudio(chatId, inputFactory.input("/files/Imagine Dragons - Enemy.mp3"))
    bot.sendPhoto(chatId, "AgACAgIAAxkDAAIiH2Zdyo_8haA51WiPm24nUe08eVwBAAJ22jEbfvjxSqkxOQjpDDJWAQADAgADcwADNQQ")
}
```