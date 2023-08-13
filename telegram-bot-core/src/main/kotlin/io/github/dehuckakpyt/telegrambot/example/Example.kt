package io.github.dehuckakpyt.telegrambot.example

import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.data.container.MassageContainer.Companion.AUDIO
import io.github.dehuckakpyt.telegrambot.data.container.MassageContainer.Companion.PHOTO
import io.github.dehuckakpyt.telegrambot.data.container.MassageContainer.Companion.TEXT


/**
 * Created on 07.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun BotHandling.example() {
    command("/some_command") {
        println(commandPathParam)
        println(commandArgument)
        println(text)
        println(chatId)
    }
    step("asd 1", type = TEXT, next = "next1") {
        println(text)
        transferToNext("this will be readable in the next step")
        // в зависимости от type здесь разные поля
        // например, в TEXT нет photo и audio
        // к тому же здесь text not null, например
        sendMessage("отправка сообщения без указания чата")
    }
    step("next1") {
        // по умолчанию будет использоваться TextMessage
        println(text)
        println(from)
        // можно получить объект, переданный на предыдущем шаге
        val transferred = transferred<String>()
        sendMessage(transferred)
    }
    step("asd 2", type = PHOTO) {
        println(photos)
        println(caption)
        // теперь не обязательно указывать чат. по-умолчанию сообщение уйдёт отправителю.
        sendMessage("сообщение уйдёт обратно")
    }
    step("asd 3", AUDIO) {
        // также теперь не нужно выставлять флаг customStep. внутри всё само определится
        println(audio)
        println(caption)

        sendMessage(chatId, "можно и указать чат, если хочется. ")
    }
    callback("call") {
        println(from.username)
        println(message)
        println(inlineMessageId)
        println(transferredOrNull<String>() ?: "ничего не передано")
    }
}