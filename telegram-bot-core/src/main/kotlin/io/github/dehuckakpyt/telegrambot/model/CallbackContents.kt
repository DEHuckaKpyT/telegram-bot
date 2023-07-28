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

class CallbackContent(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<CallbackContent>(CallbackContents)

    var content by CallbackContents.content
}