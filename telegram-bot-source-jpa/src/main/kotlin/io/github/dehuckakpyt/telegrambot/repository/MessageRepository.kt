package io.github.dehuckakpyt.telegrambot.repository

import io.github.dehuckakpyt.telegrambot.model.DatabaseMessage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/**
 * Created on 02.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface MessageRepository : JpaRepository<DatabaseMessage, UUID>