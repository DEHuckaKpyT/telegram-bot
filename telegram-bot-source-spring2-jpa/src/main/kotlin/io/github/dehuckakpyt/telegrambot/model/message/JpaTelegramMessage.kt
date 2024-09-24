package io.github.dehuckakpyt.telegrambot.model.message

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import io.hypersistence.utils.hibernate.type.array.ListArrayType
import io.hypersistence.utils.hibernate.type.json.JsonType
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_message")
@TypeDef(name = "list-array", typeClass = ListArrayType::class)
@TypeDef(name = "json", typeClass = JsonType::class)
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

    @Type(type = "list-array")
    @Column(name = "file_ids", columnDefinition = "text[]")
    override val fileIds: List<String>?,

    @Column(nullable = false)
    @ColumnDefault("'now()'")
    override val createDate: LocalDateTime = LocalDateTime.now(),

    @Column(columnDefinition = "jsonb")
    @Type(type = "json")
    override val replyMarkup: InlineKeyboardMarkup?,
) : UUIDTable(), TelegramMessage