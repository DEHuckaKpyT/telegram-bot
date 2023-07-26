package com.dehucka.telegrambot.source.chain

import com.dehucka.telegrambot.model.Chain


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface ChainSource {
    suspend fun save(chatId: Long, step: String? = null): Chain
    suspend fun save(chatId: Long, step: String? = null, content: String?): Chain
    suspend fun get(chatId: Long): Chain
    suspend fun saveContent(chatId: Long, content: String?): Chain
}