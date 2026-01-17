package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_CHAT_STATUS_EVENT_SOURCE_ENABLED
import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_ENABLED
import io.github.dehuckakpyt.telegrambot.repository.chatevent.DefaultTelegramChatStatusEventRepository
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.chatevent.DefaultTelegramChatStatusEventSource
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
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model.chatevent"])
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository.chatevent"])
@ConditionalOnExpression("\${$TELEGRAM_BOT_SOURCE_JPA_ENABLED:true} && \${$TELEGRAM_BOT_SOURCE_JPA_CHAT_STATUS_EVENT_SOURCE_ENABLED:false}")
class TelegramChatStatusEventSourceInitializationConfig {

    @Bean
    @ConditionalOnMissingBean(TelegramChatStatusEventSource::class)
    fun telegramChatStatusEventSource(
        transactionAction: TransactionAction,
        repository: DefaultTelegramChatStatusEventRepository,
        entityManager: EntityManager,
    ): DefaultTelegramChatStatusEventSource = DefaultTelegramChatStatusEventSource(transactionAction, repository, entityManager)
}