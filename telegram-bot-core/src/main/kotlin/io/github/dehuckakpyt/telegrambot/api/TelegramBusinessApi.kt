package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.type.BusinessConnection


/**
 * Created on 07.04.2024.
 *
 * @author Denis Matytsin
 */
interface TelegramBusinessApi {
    suspend fun getBusinessConnection(businessConnectionId: String): BusinessConnection
}