package io.github.dehuckakpyt.telegrambot.ext.model

import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage


/**
 * Created on 05.05.2024.
 *
 * @author Denis Matytsin
 */
val TelegramMessage.fileId: String? get() = if (fileIds.isNullOrEmpty()) null else fileIds?.get(0)