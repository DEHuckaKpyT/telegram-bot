package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambotexample.template.start

fun BotHandling.startCommand() {
    command("/start") {
        sendMessage(start)
        sendMessage("Привет, меня зовут $username :-)")
    }
}