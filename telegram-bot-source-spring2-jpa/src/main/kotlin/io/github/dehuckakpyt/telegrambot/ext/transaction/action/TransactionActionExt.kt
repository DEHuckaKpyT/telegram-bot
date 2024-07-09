package io.github.dehuckakpyt.telegrambot.ext.transaction.action

import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation


/**
 * Created on 08.07.2024.
 *
 * @author Denis Matytsin
 */
public suspend fun <T> TransactionAction.transactional(
    propagation: Propagation = Propagation.REQUIRED,
    isolation: Isolation = Isolation.DEFAULT,
    timeout: Int = -1,
    readOnly: Boolean = false,
    name: String? = null,
    block: () -> T,
): T = this.invoke(propagation, isolation, timeout, readOnly, name, block)