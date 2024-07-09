package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.PropertiesConstant.TELEGRAM_BOT_SOURCE_JPA
import io.github.dehuckakpyt.telegrambot.config.constant.PropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_CALLBACK_CONTENT_SOURCE
import io.github.dehuckakpyt.telegrambot.config.constant.PropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_CALLBACK_CONTENT_SOURCE_PER_USER
import io.github.dehuckakpyt.telegrambot.config.expression.ConfigExpression
import io.github.dehuckakpyt.telegrambot.repository.callback.JpaCallbackContentRepository
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.callback.JpaCallbackContentSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import org.springframework.beans.factory.annotation.Value
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
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model.callback"])
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository.callback"])
@ConditionalOnProperty(name = [TELEGRAM_BOT_SOURCE_JPA, TELEGRAM_BOT_SOURCE_JPA_CALLBACK_CONTENT_SOURCE], havingValue = "true", matchIfMissing = true)
class CallbackContentSourceInitializationConfig {

    @Bean
    //TODO create custom @ConditionalOnMissingBean
    @ConditionalOnMissingBean(name = ["callbackContentSourceExpression"], parameterizedContainer = [ConfigExpression::class])
    @ConditionalOnProperty(TELEGRAM_BOT_SOURCE_JPA_CALLBACK_CONTENT_SOURCE, havingValue = "true", matchIfMissing = true)
    fun callbackContentSourceExpression(
        transactionAction: TransactionAction,
        repository: JpaCallbackContentRepository,
        @Value("\${$TELEGRAM_BOT_SOURCE_JPA_CALLBACK_CONTENT_SOURCE_PER_USER}") perUser: Long,
    ): ConfigExpression<CallbackContentSource> = ConfigExpression {
        JpaCallbackContentSource(transactionAction, repository, maxCallbackContentsPerUser = perUser)
    }
}