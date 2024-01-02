package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.constant.Default.MAX_CALLBACK_CONTENTS_PER_USER
import io.github.dehuckakpyt.telegrambot.context.SpringContext.autowired
import io.github.dehuckakpyt.telegrambot.repository.CallbackContentRepository
import io.github.dehuckakpyt.telegrambot.repository.ChainRepository
import io.github.dehuckakpyt.telegrambot.repository.MessageRepository
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.callback.DatabaseCallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chain.DatabaseChainSource
import io.github.dehuckakpyt.telegrambot.source.message.DatabaseMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource


/**
 * Created on 30.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun CallbackContentSource.Companion.inDatabase(
    /**
     * Максимальное количество записей с содержанием callback'а для одного пользователя.
     * -1 для игнорирования ограничения.
     * ВАЖНО: эта проперть должна быть задана раньше, чем callbackContentSource
     */
    maxCallbackContentsPerUser: Long = MAX_CALLBACK_CONTENTS_PER_USER,
): CallbackContentSource {
    return DatabaseCallbackContentSource(
        repository = autowired<CallbackContentRepository>(),
        maxCallbackContentsPerUser = maxCallbackContentsPerUser,
    )
}

val CallbackContentSource.Companion.inDatabase: CallbackContentSource
    get() {
        return DatabaseCallbackContentSource(
            repository = autowired<CallbackContentRepository>(),
            maxCallbackContentsPerUser = MAX_CALLBACK_CONTENTS_PER_USER,
        )
    }

val ChainSource.Companion.inDatabase: ChainSource
    get() {
        return DatabaseChainSource(
            repository = autowired<ChainRepository>(),
        )
    }

val MessageSource.Companion.inDatabase: MessageSource
    get() {
        return DatabaseMessageSource(
            repository = autowired<MessageRepository>(),
        )
    }
