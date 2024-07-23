package io.github.dehuckakpyt.telegrambotexample.holder

import org.springframework.boot.context.properties.ConfigurationProperties


/**
 * Created on 23.07.2024.
 *
 * @author Denis Matytsin
 */
@ConfigurationProperties("telegram-bot.template")
class MessageTemplateHolder {
    lateinit var start: String

    lateinit var chain: String
    lateinit var chainStartSum: String
    lateinit var chainOneMore: String
    lateinit var chainEnd: String

    lateinit var register: String
    lateinit var registerContactButton: String
    lateinit var registerComplete: String
    lateinit var registerGetFirstname: String
    lateinit var registerWrongPhoneFormat: String

    lateinit var runnerNotifyWhenStarted: String
}