package io.github.dehuckakpyt.telegrambot.handler

import io.github.dehuckakpyt.telegrambot.context.SpringContext.autowired
import io.github.dehuckakpyt.telegrambot.handling.BotHandling


/**
 * Created on 21.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
//TODO remake to spring @Autowired and @PostConstruct (or any better solution)
abstract class BotHandler(block: BotHandling.() -> Unit) {
    init {
        autowired<BotHandling>().block()
    }
}