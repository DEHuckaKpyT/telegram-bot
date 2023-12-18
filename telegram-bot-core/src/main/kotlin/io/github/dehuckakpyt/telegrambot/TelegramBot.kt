package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.api.TelegramApi


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramBot : TelegramApi {
    //    fun start(): Unit
    fun stop(): Unit
}