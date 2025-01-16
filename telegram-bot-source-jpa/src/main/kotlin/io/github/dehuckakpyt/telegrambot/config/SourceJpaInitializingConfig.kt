package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionActionImpl
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.transaction.PlatformTransactionManager


/**
 * Created on 08.07.2024.
 *
 * @author Denis Matytsin
 */
@ConditionalOnProperty(name = [TELEGRAM_BOT_SOURCE_JPA], havingValue = "true", matchIfMissing = true)
class SourceJpaInitializingConfig {

    @Bean
    fun transactionAction(
        transactionManager: PlatformTransactionManager,
    ): TransactionAction =
        TransactionActionImpl(transactionManager)
}