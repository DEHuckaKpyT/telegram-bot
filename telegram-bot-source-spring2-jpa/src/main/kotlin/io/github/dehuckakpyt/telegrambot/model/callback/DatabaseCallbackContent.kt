package io.github.dehuckakpyt.telegrambot.model.callback

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import java.time.LocalDateTime
import java.util.*


/**
 * Created on 23.07.2023.
 *<p>
 * Сущность для хранения содержимого callback'ов (у которых длина боль 64 символов).
 *
 * @author Denis Matytsin
 */
@Entity
@Table(name = "callback_content")
class DatabaseCallbackContent(

    @Column(nullable = false)
    override val chatId: Long,

    @Column(nullable = false)
    override val fromId: Long,

    @Column(nullable = false)
    override var callbackId: UUID,

    @Column(nullable = false, columnDefinition = "text")
    override var content: String,

    @Column(nullable = false)
    var updateDate: LocalDateTime,
) : UUIDTable(), CallbackContent