package io.github.dehuckakpyt.telegrambot.repository.chain

import io.github.dehuckakpyt.telegrambot.model.chain.JpaChain
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/**
 * Created on 02.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface JpaChainRepository : JpaRepository<JpaChain, UUID> {

    fun findFirstByChatIdAndFromId(chatId: Long, fromId: Long): JpaChain?
}