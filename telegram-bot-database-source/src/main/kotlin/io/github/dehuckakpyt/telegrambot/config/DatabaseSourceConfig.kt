package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constans.DatabaseSourceFactory
import io.ktor.server.config.*


/**
 * Created on 30.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class DatabaseSourceConfig(config: ApplicationConfig) : DatabaseSourceFactory {

    /**
     * Максимальное количество записей с содержанием callback'а для одного пользователя.
     * -1 для игнорирования ограничения.
     */
    var maxCallbackContentsPerUser: Long = 20
}
