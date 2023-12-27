package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambotexample.template.start

fun BotHandling.startCommand() {
    command("/start") {
        sendMessage(start)
        sendMessage("Привет, меня зовут ${bot.username} :-)")
    }
}