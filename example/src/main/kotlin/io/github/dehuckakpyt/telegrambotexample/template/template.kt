package io.github.dehuckakpyt.telegrambotexample.template

import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.template.template

val BotHandling.start by template()

val BotHandling.chain by template()
val BotHandling.chainStartSum by template()
val BotHandling.chainOneMore by template()
val BotHandling.chainEnd by template()

val BotHandling.register by template()
val BotHandling.registerContactButton by template()
val BotHandling.registerComplete by template()
val BotHandling.registerGetFirstname by template()
val BotHandling.registerWrongPhoneFormat by template()