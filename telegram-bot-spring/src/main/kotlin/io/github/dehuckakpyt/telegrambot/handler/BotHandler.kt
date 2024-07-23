package io.github.dehuckakpyt.telegrambot.handler

import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.GenericApplicationContext


/**
 * Created on 21.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class BotHandler(private val block: BotHandling.() -> Unit) {

    @Autowired
    private lateinit var applicationContext: GenericApplicationContext

    @PostConstruct
    private fun initialize() {
        applicationContext.getBean(BotHandling::class.java).block()
    }
}