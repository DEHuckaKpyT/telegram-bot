package io.github.dehuckakpyt.telegrambot.template


/**
 * Created on 21.08.2023.
 *
 * Templates with messages about exceptions.
 * This messages will be shown to user.
 *
 * @author Denis Matytsin
 */
open class MessageTemplate(
    open val whenCommandNotFound: String = "An unknown command \${command} has been entered. You can view the possible actions by invoking the /help command.",
    open val whenUnexpectedMessageType: String = "A different message type is pending.",
    open val whenStepNotFound: String = "Unexpected message. You can view possible actions by calling /help.",
    open val whenKnownException: String = "\${message}",
    open val whenUnknownException: String = "An unexpected error occurred. Please contact the developer.",
)