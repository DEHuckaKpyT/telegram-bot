package io.github.dehuckakpyt.telegrambot.model.chain

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.Chain
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Entity
@Table(name = "chain")
class DatabaseChain(
    @Column(nullable = false)
    override val chatId: Long,

    @Column(nullable = false)
    override val fromId: Long,

    override var step: String?,

    @Column(columnDefinition = "text")
    override var content: String?,
) : UUIDTable(), Chain
