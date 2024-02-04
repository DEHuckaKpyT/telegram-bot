package io.github.dehuckakpyt.telegrambotexample.handling

import io.github.dehuckakpyt.telegrambot.handling.BotHandling


/**
 * Created on 04.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
fun BotHandling.testStep() {
    step("test") {
        sendMessage("saved \"$text\"")
    }
}