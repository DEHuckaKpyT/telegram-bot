package io.github.dehuckakpyt.telegrambot.ext

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection


/**
 * Created on 05.05.2024.
 *
 * @author Denis Matytsin
 */
suspend inline fun <T> readQuery(crossinline block: () -> T): T = withContext(Dispatchers.IO) {
    transaction(Connection.TRANSACTION_READ_COMMITTED, readOnly = true) {
        block()
    }
}

suspend inline fun <T> executeQuery(crossinline block: () -> T): T = withContext(Dispatchers.IO) {
    transaction(Connection.TRANSACTION_READ_COMMITTED, readOnly = false) {
        block()
    }
}