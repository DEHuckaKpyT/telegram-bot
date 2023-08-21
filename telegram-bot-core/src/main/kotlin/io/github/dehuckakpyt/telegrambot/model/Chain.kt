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

class Chain(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Chain>(Chains)

    var chatId by Chains.chatId
    var fromId by Chains.fromId
    var step by Chains.step
    var content by Chains.content
}