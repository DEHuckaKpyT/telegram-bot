package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA
import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_MESSAGE_SOURCE
import io.github.dehuckakpyt.telegrambot.config.expression.ConfigExpression
import io.github.dehuckakpyt.telegrambot.repository.message.JpaTelegramMessageRepository
import io.github.dehuckakpyt.telegrambot.source.message.JpaTelegramMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
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
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model.message"])
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository.message"])
@ConditionalOnProperty(name = [TELEGRAM_BOT_SOURCE_JPA, TELEGRAM_BOT_SOURCE_JPA_MESSAGE_SOURCE], havingValue = "true", matchIfMissing = true)
class MessageSourceInitializationConfig {

    @Bean
    //TODO create custom @ConditionalOnMissingBean
    @ConditionalOnMissingBean(name = ["telegramMessageSourceExpression"], parameterizedContainer = [ConfigExpression::class])
    @ConditionalOnProperty(TELEGRAM_BOT_SOURCE_JPA_MESSAGE_SOURCE, havingValue = "true", matchIfMissing = true)
    fun telegramMessageSourceExpression(
        transactionAction: TransactionAction,
        repository: JpaTelegramMessageRepository,
    ): ConfigExpression<MessageSource> = ConfigExpression {
        JpaTelegramMessageSource(transactionAction, repository)
    }
}