package io.github.dehuckakpyt.telegrambot.handler

import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling


/**
 * Created on 04.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class BotUpdateHandler(internal val block: BotUpdateHandling.() -> Unit)
