package io.github.dehuckakpyt.telegrambot.handler

import io.github.dehuckakpyt.telegrambot.handling.BotHandling


/**
 * Created on 21.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class BotHandler(internal val block: BotHandling.() -> Unit)
