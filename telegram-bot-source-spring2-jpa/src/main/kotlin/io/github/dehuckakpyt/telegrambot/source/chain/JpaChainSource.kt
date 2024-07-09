package io.github.dehuckakpyt.telegrambot.source.chain

import io.github.dehuckakpyt.telegrambot.model.chain.JpaChain
import io.github.dehuckakpyt.telegrambot.repository.chain.JpaChainRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction

open class JpaChainSource(
    private val transactional: TransactionAction,
    private val repository: JpaChainRepository,
) : ChainSource {

    override suspend fun save(chatId: Long, fromId: Long, step: String?, content: String?): Unit = transactional {
        val chain = repository.findFirstByChatIdAndFromId(chatId, fromId)?.apply {
            this.step = step
            this.content = content
        } ?: JpaChain(
            chatId = chatId,
            fromId = fromId,
            step = step,
            content = content,
        )

        repository.save(chain)
    }

    override suspend fun get(chatId: Long, fromId: Long): JpaChain? = transactional(readOnly = true) {
        repository.findFirstByChatIdAndFromId(chatId, fromId)
    }
}