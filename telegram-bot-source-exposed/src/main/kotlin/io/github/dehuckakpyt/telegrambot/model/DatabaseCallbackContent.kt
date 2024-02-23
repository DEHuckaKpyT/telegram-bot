package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.datetime
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
    val callbackId = uuid("callback_id").uniqueIndex()
    val content = text("content")
    val updateDate = datetime("update_date")
}

class DatabaseCallbackContent(id: EntityID<UUID>) : UUIDEntity(id), CallbackContent {
    companion object : UUIDEntityClass<DatabaseCallbackContent>(CallbackContents)

    override var chatId by CallbackContents.chatId
    override var fromId by CallbackContents.fromId
    override var callbackId by CallbackContents.callbackId
    override var content by CallbackContents.content
    var updateDate by CallbackContents.updateDate
}