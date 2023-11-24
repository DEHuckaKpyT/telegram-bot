package io.github.dehuckakpyt.telegrambot


/**
 * Created on 24.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class BotHandler(block: BotHandler.() -> Unit = {}) : BotHandling() {
    init {
        block()
    }
}