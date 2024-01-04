package io.github.dehuckakpyt.telegrambot.annotation

import io.github.dehuckakpyt.telegrambot.config.TelegramBotInitializationConfig
import org.springframework.context.annotation.Import
import java.lang.annotation.Inherited


/**
 * Created on 29.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
@Import(TelegramBotInitializationConfig::class)
annotation class EnableTelegramBot