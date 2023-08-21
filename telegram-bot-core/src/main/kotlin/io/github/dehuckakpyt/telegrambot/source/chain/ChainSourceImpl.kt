package io.github.dehuckakpyt.telegrambot.source.chain

import com.dehucka.exposed.ext.execute
import com.dehucka.exposed.ext.read
import com.dehucka.microservice.logger.Logging
import io.github.dehuckakpyt.telegrambot.model.Chain
import io.github.dehuckakpyt.telegrambot.model.Chains
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and

class ChainSourceImpl : ChainSource, Logging {

    override suspend fun save(chatId: Long, fromId: Long?, step: String?, content: String?) = execute {
        fromId ?: let {
            logger.warn("Don't expect message without fromId.\nchatId = '$chatId'")
            return@execute
        }

        Chain.find((Chains.chatId eq chatId) and (Chains.fromId eq fromId)).firstOrNull()?.apply {
            this.step = step
            this.content = content
        } ?: Chain.new {
            this.chatId = chatId
            this.fromId = fromId
            this.step = step
            this.content = content
        }
    }

    override suspend fun get(chatId: Long, fromId: Long?): Chain? = read {
        fromId ?: let {
            logger.warn("Don't expect message without fromId.\nchatId = '$chatId'")
            return@read null
        }

        Chain.find((Chains.chatId eq chatId) and (Chains.fromId eq fromId)).firstOrNull()
    }
}