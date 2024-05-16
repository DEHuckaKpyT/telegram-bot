package io.github.dehuckakpyt.telegrambot.ext.container

import io.github.dehuckakpyt.telegrambot.container.Container


/**
 * Created on 01.05.2024.
 *
 * @author Denis Matytsin
 */
public val Container.chatId: Long get() = chat.id
public val Container.fromId: Long get() = from.id