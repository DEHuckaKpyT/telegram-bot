package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.api.TelegramApi
import io.github.dehuckakpyt.telegrambot.api.util.TelegramUtilApi


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramBot : TelegramApi, TelegramUtilApi {

    val username: String
    fun stop(): Unit

    companion object
}