package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_ENABLED
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionActionImpl
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.transaction.PlatformTransactionManager


/**
 * @author Denis Matytsin
 */
@AutoConfiguration
@ConditionalOnProperty(name = [TELEGRAM_BOT_SOURCE_JPA_ENABLED], havingValue = "true", matchIfMissing = true)
class SourceJpaInitializingConfig {

    @Bean
    fun transactionAction(
        transactionManager: PlatformTransactionManager,
    ): TransactionAction = TransactionActionImpl(transactionManager)
}