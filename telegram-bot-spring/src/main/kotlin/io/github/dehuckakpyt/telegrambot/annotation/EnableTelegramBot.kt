package io.github.dehuckakpyt.telegrambot.annotation

import java.lang.annotation.Inherited


/**
 * @author Denis Matytsin
 */
@Deprecated("Now will be in autoconfiguration. Annotation no more needed.")
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
annotation class EnableTelegramBot