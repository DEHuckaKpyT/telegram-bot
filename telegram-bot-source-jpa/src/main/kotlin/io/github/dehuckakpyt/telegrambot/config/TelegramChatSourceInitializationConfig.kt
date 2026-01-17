package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_CHAT_SOURCE_ENABLED
import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_ENABLED
import io.github.dehuckakpyt.telegrambot.repository.chat.DefaultTelegramChatRepository
import io.github.dehuckakpyt.telegrambot.source.chat.DefaultTelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.persistence.EntityManager
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


/**
 * @author Denis Matytsin
 */
@AutoConfiguration
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model.chat"])
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository.chat"])
@ConditionalOnExpression("\${$TELEGRAM_BOT_SOURCE_JPA_ENABLED:true} && \${$TELEGRAM_BOT_SOURCE_JPA_CHAT_SOURCE_ENABLED:false}")
class TelegramChatSourceInitializationConfig {

    @Bean
    @ConditionalOnMissingBean(TelegramChatSource::class)
    fun telegramChatSource(
        transactionAction: TransactionAction,
        repository: DefaultTelegramChatRepository,
        entityManager: EntityManager,
    ): DefaultTelegramChatSource = DefaultTelegramChatSource(transactionAction, repository, entityManager)
}