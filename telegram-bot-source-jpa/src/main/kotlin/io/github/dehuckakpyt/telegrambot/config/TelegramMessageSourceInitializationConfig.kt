package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_ENABLED
import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_MESSAGE_SOURCE_ENABLED
import io.github.dehuckakpyt.telegrambot.repository.message.DefaultTelegramMessageRepository
import io.github.dehuckakpyt.telegrambot.source.message.DefaultTelegramMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.TelegramMessageSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.persistence.EntityManager
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


/**
 * @author Denis Matytsin
 */
@AutoConfiguration
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model.message"])
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository.message"])
@ConditionalOnProperty(name = [TELEGRAM_BOT_SOURCE_JPA_ENABLED, TELEGRAM_BOT_SOURCE_JPA_MESSAGE_SOURCE_ENABLED], havingValue = "true", matchIfMissing = true)
class TelegramMessageSourceInitializationConfig {

    @Bean
    @ConditionalOnMissingBean(TelegramMessageSource::class)
    fun telegramMessageSource(
        transactionAction: TransactionAction,
        repository: DefaultTelegramMessageRepository,
        entityManager: EntityManager,
    ): DefaultTelegramMessageSource = DefaultTelegramMessageSource(transactionAction, repository, entityManager)
}