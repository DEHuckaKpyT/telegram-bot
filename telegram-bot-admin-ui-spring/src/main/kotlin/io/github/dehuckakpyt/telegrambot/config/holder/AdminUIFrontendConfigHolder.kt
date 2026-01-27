package io.github.dehuckakpyt.telegrambot.config.holder

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


/**
 * @author Denis Matytsin
 */
@ConfigurationProperties(prefix = "telegram-bot.administration.admin-ui.front-end")
class AdminUIFrontendConfigHolder @ConstructorBinding constructor(

    val host: String,
) 