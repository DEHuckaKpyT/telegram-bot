package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.callback.InMemoryCallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chain.InMemoryChainSource
import io.github.dehuckakpyt.telegrambot.source.message.EmptyMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
val CallbackContentSource.Companion.inMemory: CallbackContentSource
    get() = InMemoryCallbackContentSource()
val ChainSource.Companion.inMemory: ChainSource
    get() = InMemoryChainSource()
val MessageSource.Companion.empty: MessageSource
    get() = EmptyMessageSource()