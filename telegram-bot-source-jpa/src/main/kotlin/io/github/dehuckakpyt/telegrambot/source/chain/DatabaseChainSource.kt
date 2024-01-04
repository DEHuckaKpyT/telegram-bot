package io.github.dehuckakpyt.telegrambot.source.chain

import io.github.dehuckakpyt.telegrambot.model.chain.DatabaseChain
import io.github.dehuckakpyt.telegrambot.repository.chain.DatabaseChainRepository
import org.springframework.transaction.annotation.Transactional

internal class DatabaseChainSource(
    private val repository: DatabaseChainRepository,
) : ChainSource {

    @Transactional
    override suspend fun save(chatId: Long, fromId: Long, step: String?, content: String?): Unit {
        val chain = get(chatId, fromId)?.apply {
            this.step = step
            this.content = content
        } ?: DatabaseChain(
            chatId = chatId,
            fromId = fromId,
            step = step,
            content = content,
        )

        repository.save(chain)
    }

    @Transactional(readOnly = true)
    override suspend fun get(chatId: Long, fromId: Long): DatabaseChain? = repository.findFirstByChatIdAndFromId(chatId, fromId)
}