package io.github.dehuckakpyt.telegrambot.source.chain

import io.github.dehuckakpyt.telegrambot.model.source.Chain
import io.github.dehuckakpyt.telegrambot.model.source.ChainImpl


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class InMemoryChainSource : ChainSource {

    private val chains: MutableMap<Long, MutableMap<Long, ChainImpl>> = mutableMapOf()

    override suspend fun save(chatId: Long, fromId: Long, step: String?, content: String?) {
        val chainByFromId = chains.getOrPut(chatId) { mutableMapOf() }
        val chain = chainByFromId.getOrPut(fromId) { ChainImpl(chatId, fromId) }

        chain.apply {
            this.step = step
            this.content = content
        }
    }

    override suspend fun get(chatId: Long, fromId: Long): Chain? = chains.get(chatId)?.get(fromId)
}
