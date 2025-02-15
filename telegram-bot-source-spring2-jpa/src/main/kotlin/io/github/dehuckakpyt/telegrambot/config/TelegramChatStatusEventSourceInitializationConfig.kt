package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.PropertiesConstant.TELEGRAM_BOT_SOURCE_JPA
import io.github.dehuckakpyt.telegrambot.config.constant.PropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_CHAT_STATUS_EVENT_SOURCE
import io.github.dehuckakpyt.telegrambot.config.expression.ConfigExpression
import io.github.dehuckakpyt.telegrambot.repository.chatevent.JpaTelegramChatStatusEventRepository
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.chatevent.JpaTelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


/**
 * Created on 22.06.2024.
 *
 * @author Denis Matytsin
 */
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model.chatevent"])
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository.chatevent"])
@ConditionalOnExpression("\${$TELEGRAM_BOT_SOURCE_JPA:true} && \${$TELEGRAM_BOT_SOURCE_JPA_CHAT_STATUS_EVENT_SOURCE:false}")
class TelegramChatStatusEventSourceInitializationConfig {

    @Bean
    //TODO create custom @ConditionalOnMissingBean
    @ConditionalOnMissingBean(name = ["telegramChatStatusEventSourceExpression"], parameterizedContainer = [ConfigExpression::class])
    @ConditionalOnProperty(TELEGRAM_BOT_SOURCE_JPA_CHAT_STATUS_EVENT_SOURCE, havingValue = "true", matchIfMissing = true)
    fun telegramChatStatusEventSourceExpression(
        transactionAction: TransactionAction,
        repository: JpaTelegramChatStatusEventRepository,
    ): ConfigExpression<TelegramChatStatusEventSource> = ConfigExpression {
        JpaTelegramChatStatusEventSource(transactionAction, repository)
    }
}