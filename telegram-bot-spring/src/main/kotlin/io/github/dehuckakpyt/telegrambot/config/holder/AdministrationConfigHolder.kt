package io.github.dehuckakpyt.telegrambot.config.holder

import org.springframework.boot.context.properties.ConfigurationProperties


/**
 * @author Denis Matytsin
 */
@ConfigurationProperties(prefix = "telegram-bot.administration")
class AdministrationConfigHolder(

    val access: AdministrationAccessConfigHolder = AdministrationAccessConfigHolder(),
) {
    class AdministrationAccessConfigHolder(
        val userIds: Set<Long> = emptySet(),
    )
}