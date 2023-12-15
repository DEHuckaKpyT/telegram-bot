package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.type.InlineQueryResult
import io.github.dehuckakpyt.telegrambot.model.type.InlineQueryResultsButton
import io.github.dehuckakpyt.telegrambot.model.type.SentWebAppMessage


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 */
interface TelegramInlineModeApi {
    suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int? = null,
        isPersonal: Boolean? = null,
        nextOffset: String? = null,
        button: InlineQueryResultsButton? = null,
    ): Boolean

    suspend fun answerWebAppQuery(
        webAppQueryId: String,
        result: InlineQueryResult
    ): SentWebAppMessage
}