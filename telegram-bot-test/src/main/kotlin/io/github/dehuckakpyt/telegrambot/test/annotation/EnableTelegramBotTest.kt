package io.github.dehuckakpyt.telegrambot.test.annotation

import io.github.dehuckakpyt.telegrambot.test.TelegramBotUpdateManager
import java.lang.annotation.Inherited


/**
 * Created on 13.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
public annotation class EnableTelegramBotTest {
    companion object {
        init {
            TelegramBotUpdateManager
        }
    }
}
