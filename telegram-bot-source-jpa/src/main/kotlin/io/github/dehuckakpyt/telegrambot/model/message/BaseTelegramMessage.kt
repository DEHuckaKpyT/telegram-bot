package io.github.dehuckakpyt.telegrambot.model.message

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.hypersistence.utils.hibernate.type.array.ListArrayType
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*


/**
 * @author Denis Matytsin
 */
@MappedSuperclass
class BaseTelegramMessage : UUIDTable(), TelegramMessage<UUID> {

    @Column(nullable = false)
    override var chatId: Long = 0

    override var fromId: Long? = null

    @Column(nullable = false)
    override var fromBot: Boolean = false

    @Column(nullable = false)
    override var messageId: Long = 0

    @Column(nullable = false)
    override lateinit var type: String

    override var step: String? = null

    override var stepContainerType: String? = null

    @Column(columnDefinition = "text")
    override var text: String? = null

    @Type(ListArrayType::class)
    @Column(name = "file_ids", columnDefinition = "text[]")
    override var fileIds: List<String>? = null

    @Column(nullable = false)
    override lateinit var createdAt: LocalDateTime
}