package io.github.dehuckakpyt.telegrambot.transaction.action

import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation


/**
 * Created on 05.07.2024.
 *
 * @author Denis Matytsin
 */
interface TransactionAction {

    public suspend operator fun <T> invoke(
        propagation: Propagation = Propagation.REQUIRED,
        isolation: Isolation = Isolation.DEFAULT,
        timeout: Int = -1,
        readOnly: Boolean = false,
        name: String? = null,
        block: () -> T,
    ): T
}