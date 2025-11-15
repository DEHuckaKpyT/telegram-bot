package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA
import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_USER_SOURCE
import io.github.dehuckakpyt.telegrambot.repository.user.DefaultTelegramUserRepository
import io.github.dehuckakpyt.telegrambot.source.user.DefaultTelegramUserSource
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.persistence.EntityManager
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
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model.user"])
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository.user"])
@ConditionalOnProperty(name = [TELEGRAM_BOT_SOURCE_JPA, TELEGRAM_BOT_SOURCE_JPA_USER_SOURCE], havingValue = "true", matchIfMissing = true)
class TelegramUserSourceInitializationConfig {

    @Bean
    @ConditionalOnMissingBean(TelegramUserSource::class)
    fun telegramUserSource(
        transactionAction: TransactionAction,
        repository: DefaultTelegramUserRepository,
        entityManager: EntityManager,
    ): DefaultTelegramUserSource = DefaultTelegramUserSource(transactionAction, repository, entityManager)
}