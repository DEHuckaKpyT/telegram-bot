package io.github.dehuckakpyt.telegrambot.handler

import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.start

fun BotHandling.startCommand() {
    command("/start") {
        sendMessage(start)
    }
}