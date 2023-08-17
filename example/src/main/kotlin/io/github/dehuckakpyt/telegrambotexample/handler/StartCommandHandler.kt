package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambotexample.start

fun BotHandling.startCommand() {
    command("/start") {
        sendMessage(start)
    }
}