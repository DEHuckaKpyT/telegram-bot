package io.github.dehuckakpyt.telegrambot.transaction.definition

import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation


/**
 * Created on 05.07.2024.
 *
 * @author Denis Matytsin
 */
data class SimpleTransactionDefinition(
    val propagation: Propagation = Propagation.REQUIRED,
    val isolation: Isolation = Isolation.DEFAULT,
    @JvmField val timeout: Int = -1,
    val readOnly: Boolean = false,
    @JvmField val name: String? = null,
) : TransactionDefinition {

    override fun getPropagationBehavior(): Int = propagation.value()
    override fun getIsolationLevel(): Int = isolation.value()
    override fun getTimeout(): Int = timeout
    override fun isReadOnly(): Boolean = readOnly
    override fun getName(): String? = name
}
