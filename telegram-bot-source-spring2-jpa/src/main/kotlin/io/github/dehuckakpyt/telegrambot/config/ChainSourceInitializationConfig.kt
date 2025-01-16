package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA
import io.github.dehuckakpyt.telegrambot.config.constant.SourceJpaPropertiesConstant.TELEGRAM_BOT_SOURCE_JPA_CHAIN_SOURCE
import io.github.dehuckakpyt.telegrambot.config.expression.ConfigExpression
import io.github.dehuckakpyt.telegrambot.repository.chain.JpaChainRepository
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chain.JpaChainSource
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
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model.chain"])
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository.chain"])
@ConditionalOnProperty(name = [TELEGRAM_BOT_SOURCE_JPA, TELEGRAM_BOT_SOURCE_JPA_CHAIN_SOURCE], havingValue = "true", matchIfMissing = true)
class ChainSourceInitializationConfig {

    @Bean
    //TODO create custom @ConditionalOnMissingBean
    @ConditionalOnMissingBean(name = ["chainSourceExpression"], parameterizedContainer = [ConfigExpression::class])
    @ConditionalOnProperty(TELEGRAM_BOT_SOURCE_JPA_CHAIN_SOURCE, havingValue = "true", matchIfMissing = true)
    fun chainSourceExpression(
        transactionAction: TransactionAction,
        repository: JpaChainRepository,
    ): ConfigExpression<ChainSource> = ConfigExpression {
        JpaChainSource(transactionAction, repository)
    }
}