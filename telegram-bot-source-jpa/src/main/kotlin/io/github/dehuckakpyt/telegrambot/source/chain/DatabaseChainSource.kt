package io.github.dehuckakpyt.telegrambot.source.chain

import io.github.dehuckakpyt.telegrambot.model.DatabaseChain
import io.github.dehuckakpyt.telegrambot.repository.ChainRepository
import org.springframework.transaction.annotation.Transactional

internal class DatabaseChainSource(
    private val repository: ChainRepository,
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