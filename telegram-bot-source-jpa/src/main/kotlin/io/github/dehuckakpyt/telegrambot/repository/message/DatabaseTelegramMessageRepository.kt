package io.github.dehuckakpyt.telegrambot.repository.message

import io.github.dehuckakpyt.telegrambot.model.message.DatabaseTelegramMessage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/**
 * Created on 02.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface DatabaseTelegramMessageRepository : JpaRepository<DatabaseTelegramMessage, UUID>