package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.type.PassportElementError


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 */
interface TelegramPassportApi {
    suspend fun setPassportDataErrors(
        userId: Long,
        errors: List<PassportElementError>
    ): Boolean
}