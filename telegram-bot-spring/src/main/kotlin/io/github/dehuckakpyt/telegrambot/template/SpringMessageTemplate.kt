package io.github.dehuckakpyt.telegrambot.template

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


/**
 * Created on 26.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Component
@ConfigurationProperties("telegram-bot.template")
class SpringMessageTemplate : MessageTemplate() {
    override val whenCommandNotFound: String = "Введена неизвестная команда \${command}. Посмотреть возможные действия можно, вызвав команду /help."
    override val whenUnexpectedMessageType: String = "Ожидается сообщение другого типа."
    override val whenStepNotFound: String = "Неожидаемое сообщение. Посмотреть возможные действия можно, вызвав команду /help."
    override val whenKnownException: String = "\${message}"
    override val whenUnknownException: String = "Произошла непредвиденная ошибка. Обратитесь к разработчику."
}