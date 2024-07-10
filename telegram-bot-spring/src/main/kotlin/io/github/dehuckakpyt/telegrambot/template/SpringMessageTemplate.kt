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
class SpringMessageTemplate(
    override val whenCommandNotFound: String = "An unknown command \${command} has been entered. You can view the possible actions by invoking the /help command.",
    override val whenUnexpectedMessageType: String = "A different message type is pending.",
    override val whenStepNotFound: String = "Unexpected message. You can view possible actions by calling /help.",
    override val whenKnownException: String = "\${message}",
    override val whenUnknownException: String = "An unexpected error occurred. Please contact the developer.",
) : MessageTemplate()