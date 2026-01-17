package io.github.dehuckakpyt.telegrambot.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.*


/**
 * @author Denis Matytsin
 */
@MappedSuperclass
abstract class UUIDTable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    lateinit var id: UUID

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