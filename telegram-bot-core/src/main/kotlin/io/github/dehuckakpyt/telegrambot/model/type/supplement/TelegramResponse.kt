package io.github.dehuckakpyt.telegrambot.model.type.supplement

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 05.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
data class TelegramResponse<T>(
    @param:JsonProperty("ok") val ok: Boolean,
    @param:JsonProperty("result") val result: T?,
    @param:JsonProperty("error_code") val errorCode: Int?,
    @param:JsonProperty("description") val description: String?,
)