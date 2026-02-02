package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.Chain
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.dao.java.UUIDEntity
import org.jetbrains.exposed.v1.dao.java.UUIDEntityClass
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
