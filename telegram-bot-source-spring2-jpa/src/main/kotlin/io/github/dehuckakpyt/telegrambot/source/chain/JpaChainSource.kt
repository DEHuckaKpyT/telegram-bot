package io.github.dehuckakpyt.telegrambot.source.chain

import io.github.dehuckakpyt.telegrambot.model.chain.JpaChain
import io.github.dehuckakpyt.telegrambot.repository.chain.JpaChainRepository
import org.springframework.transaction.annotation.Transactional

open class JpaChainSource(
    private val repository: JpaChainRepository,
) : ChainSource {

    @Transactional
    override suspend fun save(chatId: Long, fromId: Long, step: String?, content: String?): Unit {
        val chain = get(chatId, fromId)?.apply {
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

    @Transactional(readOnly = true)
    override suspend fun get(chatId: Long, fromId: Long): JpaChain? = repository.findFirstByChatIdAndFromId(chatId, fromId)
}