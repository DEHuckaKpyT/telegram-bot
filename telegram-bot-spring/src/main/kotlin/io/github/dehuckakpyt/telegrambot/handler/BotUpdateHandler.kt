package io.github.dehuckakpyt.telegrambot.handler

import io.github.dehuckakpyt.telegrambot.context.SpringContext.autowired
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling


/**
 * Created on 04.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class BotUpdateHandler(block: BotUpdateHandling.() -> Unit) {
    init {
        autowired<BotUpdateHandling>().block()
    }
}