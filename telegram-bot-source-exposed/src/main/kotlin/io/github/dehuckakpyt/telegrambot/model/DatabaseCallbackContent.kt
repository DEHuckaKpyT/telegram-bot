package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.core.java.javaUUID
import org.jetbrains.exposed.v1.dao.java.UUIDEntity
import org.jetbrains.exposed.v1.dao.java.UUIDEntityClass
import org.jetbrains.exposed.v1.javatime.datetime
import java.util.*


/**
 * Created on 23.07.2023.
 *<p>
 * Сущность для хранения содержимого callback'ов (у которых длина боль 64 символов).
 *
 * @author Denis Matytsin
 */
object CallbackContents : UUIDTable("callback_content") {
    val chatId = long("chat_id")
    val fromId = long("from_id")
    val callbackId = javaUUID("callback_id").uniqueIndex()
    val content = text("content")
    val updatedAt = datetime("updated_at")
}

class DatabaseCallbackContent(id: EntityID<UUID>) : UUIDEntity(id), CallbackContent {
    companion object : UUIDEntityClass<DatabaseCallbackContent>(CallbackContents)

    override var chatId by CallbackContents.chatId
    override var fromId by CallbackContents.fromId
    override var callbackId by CallbackContents.callbackId
    override var content by CallbackContents.content
    var updatedAt by CallbackContents.updatedAt
}