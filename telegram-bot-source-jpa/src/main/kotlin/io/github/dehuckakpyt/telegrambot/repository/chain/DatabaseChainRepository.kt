package io.github.dehuckakpyt.telegrambot.repository.chain

import io.github.dehuckakpyt.telegrambot.model.chain.DatabaseChain
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/**
 * Created on 02.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface DatabaseChainRepository : JpaRepository<DatabaseChain, UUID> {

    fun findFirstByChatIdAndFromId(chatId: Long, fromId: Long): DatabaseChain?
}