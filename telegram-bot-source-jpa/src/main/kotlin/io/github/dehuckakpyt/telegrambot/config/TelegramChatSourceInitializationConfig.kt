package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA
import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_CHAT_SOURCE
import io.github.dehuckakpyt.telegrambot.config.expression.ConfigExpression
import io.github.dehuckakpyt.telegrambot.repository.chat.JpaTelegramChatRepository
import io.github.dehuckakpyt.telegrambot.source.chat.JpaTelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource
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
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model.chat"])
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository.chat"])
@ConditionalOnExpression("\${$TELEGRAM_BOT_SOURCE_JPA:true} && \${$TELEGRAM_BOT_SOURCE_JPA_CHAT_SOURCE:false}")
class TelegramChatSourceInitializationConfig {

    @Bean
    //TODO create custom @ConditionalOnMissingBean
    @ConditionalOnMissingBean(name = ["telegramChatSourceExpression"], parameterizedContainer = [ConfigExpression::class])
    @ConditionalOnProperty(TELEGRAM_BOT_SOURCE_JPA_CHAT_SOURCE, havingValue = "true", matchIfMissing = true)
    fun telegramChatSourceExpression(
        transactionAction: TransactionAction,
        repository: JpaTelegramChatRepository,
    ): ConfigExpression<TelegramChatSource> = ConfigExpression {
        JpaTelegramChatSource(transactionAction, repository)
    }
}