package io.github.dehuckakpyt.telegrambot.template


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class MessageTemplate(
    open val whenCommandNotFound: String = "Введена неизвестная команда \${command}. Посмотреть возможные действия можно, вызвав команду /help.",
    open val whenUnexpectedMessageType: String = "Ожидается сообщение другого типа.",
    open val whenStepNotFound: String = "Неожидаемое сообщение. Посмотреть возможные действия можно, вызвав команду /help.",
    open val whenKnownException: String = "\${message}",
    open val whenUnknownException: String = "Произошла непредвиденная ошибка. Обратитесь к разработчику.",
)