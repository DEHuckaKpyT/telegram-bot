package io.github.dehuckakpyt.telegrambot.handler

import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.GenericApplicationContext


/**
 * Created on 04.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class BotUpdateHandler(private val block: BotUpdateHandling.() -> Unit) : InitializingBean {

    @Autowired
    private lateinit var applicationContext: GenericApplicationContext

    override fun afterPropertiesSet() {
        applicationContext.getBean(BotUpdateHandling::class.java).block()
    }
}