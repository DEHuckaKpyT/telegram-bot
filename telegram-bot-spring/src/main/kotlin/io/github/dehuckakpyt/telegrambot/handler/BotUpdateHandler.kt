package io.github.dehuckakpyt.telegrambot.handler

import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.GenericApplicationContext


/**
 * Created on 04.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class BotUpdateHandler(private val block: BotUpdateHandling.() -> Unit) {

    @Autowired
    private lateinit var applicationContext: GenericApplicationContext

    @PostConstruct
    private fun initialize() {
        applicationContext.getBean(BotUpdateHandling::class.java).block()
    }
}