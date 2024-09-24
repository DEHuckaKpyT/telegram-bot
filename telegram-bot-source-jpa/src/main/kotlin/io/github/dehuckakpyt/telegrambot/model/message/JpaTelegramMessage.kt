package io.github.dehuckakpyt.telegrambot.model.message

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import io.hypersistence.utils.hibernate.type.array.ListArrayType
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Type
import java.time.LocalDateTime


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_message")
class JpaTelegramMessage(
    @Column(nullable = false)
    override val chatId: Long,

    @Column(nullable = false)
    override val fromId: Long,

    @Column(nullable = false)
    override val fromBot: Boolean,

    @Column(nullable = false)
    override val messageId: Long,

    @Column(nullable = false)
    override val type: String,

    override val step: String?,

    override val stepContainerType: String?,

    @Column(columnDefinition = "text")
    override val text: String?,

    @Type(ListArrayType::class)
    @Column(name = "file_ids", columnDefinition = "text[]")
    override val fileIds: List<String>?,

    @Column(nullable = false)
    @ColumnDefault("'now()'")
    override val createDate: LocalDateTime = LocalDateTime.now(),

    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    override val replyMarkup: InlineKeyboardMarkup?,
) : UUIDTable(), TelegramMessage