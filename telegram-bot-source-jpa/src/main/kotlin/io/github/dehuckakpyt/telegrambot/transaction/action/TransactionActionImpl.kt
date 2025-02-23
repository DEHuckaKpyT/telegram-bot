package io.github.dehuckakpyt.telegrambot.transaction.action

import io.github.dehuckakpyt.telegrambot.transaction.definition.SimpleTransactionDefinition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.support.TransactionTemplate


/**
 * Created on 08.07.2024.
 *
 * @author Denis Matytsin
 */
class TransactionActionImpl(
    private val transactionManager: PlatformTransactionManager,
) : TransactionAction {
    private val templates: MutableMap<SimpleTransactionDefinition, TransactionTemplate> = mutableMapOf()

    public override suspend operator fun <T> invoke(
        propagation: Propagation,
        isolation: Isolation,
        timeout: Int,
        readOnly: Boolean,
        name: String?,
        block: () -> T,
    ): T {
        val template = SimpleTransactionDefinition(propagation, isolation, timeout, readOnly, name).let(::getTemplate)

        return withContext(Dispatchers.IO) {
            template.execute { block() }
        }
    }

    private fun getTemplate(definition: SimpleTransactionDefinition): TransactionTemplate =
        templates.getOrPut(definition) { TransactionTemplate(transactionManager, definition) }
}