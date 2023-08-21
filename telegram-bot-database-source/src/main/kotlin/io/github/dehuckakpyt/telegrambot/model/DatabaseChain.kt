package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
object Chains : UUIDTable("chain") {

    val chatId = long("chat_id")
    val fromId = long("from_id")
    val step = varchar("step", 255).nullable()
    val content = text("content").nullable()

    init {
        uniqueIndex(chatId, fromId)
    }
}

class DatabaseChain(id: EntityID<UUID>) : UUIDEntity(id), Chain {
    companion object : UUIDEntityClass<DatabaseChain>(Chains)

    override var chatId by Chains.chatId
    override var fromId by Chains.fromId
    override var step by Chains.step
    override var content by Chains.content
}
