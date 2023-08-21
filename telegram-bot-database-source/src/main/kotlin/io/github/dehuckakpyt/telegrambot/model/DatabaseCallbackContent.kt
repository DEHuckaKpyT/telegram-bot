package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*


/**
 * Created on 23.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
object CallbackContents : UUIDTable("callback_content") {
    val content = text("content")
}

class DatabaseCallbackContent(id: EntityID<UUID>) : UUIDEntity(id), CallbackContent {
    companion object : UUIDEntityClass<DatabaseCallbackContent>(CallbackContents)

    override val identifier: UUID get() = id.value
    override var content by CallbackContents.content
}