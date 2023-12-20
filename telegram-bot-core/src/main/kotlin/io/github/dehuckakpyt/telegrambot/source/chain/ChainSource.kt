package io.github.dehuckakpyt.telegrambot.source.chain

import com.dehucka.microservice.logger.Logging
import io.github.dehuckakpyt.telegrambot.model.source.Chain


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface ChainSource : Logging {

    suspend fun save(chatId: Long, fromId: Long, step: String?, content: String?)

    suspend fun get(chatId: Long, fromId: Long): Chain?

    companion object
}