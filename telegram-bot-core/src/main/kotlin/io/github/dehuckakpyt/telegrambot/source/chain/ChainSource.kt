package io.github.dehuckakpyt.telegrambot.source.chain

import com.dehucka.microservice.logger.Logging
import io.github.dehuckakpyt.telegrambot.model.Chain


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface ChainSource : Logging {
    suspend fun save(chatId: Long, fromId: Long?, step: String?, content: String?) {
        fromId ?: let {
            logger.warn("Don't expect message without fromId.\nchatId = '$chatId'")
            return
        }

        save(chatId, fromId, step, content)
    }

    suspend fun save(chatId: Long, fromId: Long, step: String?, content: String?)

    suspend fun get(chatId: Long, fromId: Long?): Chain? {
        fromId ?: let {
            logger.warn("Don't expect message without fromId.\nchatId = '$chatId'")
            return null
        }

        return get(chatId, fromId)
    }

    suspend fun get(chatId: Long, fromId: Long): Chain?

    companion object
}