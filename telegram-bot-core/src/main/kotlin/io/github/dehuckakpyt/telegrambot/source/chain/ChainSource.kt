package io.github.dehuckakpyt.telegrambot.source.chain

import io.github.dehuckakpyt.telegrambot.model.Chain


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface ChainSource {
    suspend fun save(chatId: Long, fromId: Long?, step: String?, content: String?)
    suspend fun get(chatId: Long, fromId: Long?): Chain?
}