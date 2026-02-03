package io.github.dehuckakpyt.telegrambot.config.holder

import org.springframework.boot.context.properties.ConfigurationProperties


/**
 * @author Denis Matytsin
 */
@ConfigurationProperties(prefix = "telegram-bot.administration.admin-panel.front-end-config")
class AdminPanelConfigHolder(

    val defaultPageSize: Int = 10,
)