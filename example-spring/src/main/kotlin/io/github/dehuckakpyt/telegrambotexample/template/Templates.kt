package io.github.dehuckakpyt.telegrambotexample.template

import io.github.dehuckakpyt.telegrambot.factory.template.TemplateFactory.property
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambotexample.runner.NotifyWhenStartedRunner

val BotHandling.start by property()

val BotHandling.chain by property()
val BotHandling.chainStartSum by property()
val BotHandling.chainOneMore by property()
val BotHandling.chainEnd by property()

val BotHandling.register by property()
val BotHandling.registerContactButton by property()
val BotHandling.registerComplete by property()
val BotHandling.registerGetFirstname by property()
val BotHandling.registerWrongPhoneFormat by property()

val NotifyWhenStartedRunner.runnerNotifyWhenStarted by property()