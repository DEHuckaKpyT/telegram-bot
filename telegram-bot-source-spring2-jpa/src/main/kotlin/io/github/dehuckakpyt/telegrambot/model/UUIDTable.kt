package io.github.dehuckakpyt.telegrambot.model

import java.util.*
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass


/**
 * Created on 02.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
@MappedSuperclass
abstract class UUIDTable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UUIDTable

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "UUIDTable(id=$id)"
    }
}