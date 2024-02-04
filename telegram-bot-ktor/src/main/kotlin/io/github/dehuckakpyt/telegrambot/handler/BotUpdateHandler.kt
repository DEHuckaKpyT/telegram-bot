package io.github.dehuckakpyt.telegrambot.handler

import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import org.koin.mp.KoinPlatform


/**
 * Created on 04.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class BotUpdateHandler(block: BotUpdateHandling.() -> Unit) {
    init {
        KoinPlatform.getKoin().get<BotUpdateHandling>().block()
    }
}