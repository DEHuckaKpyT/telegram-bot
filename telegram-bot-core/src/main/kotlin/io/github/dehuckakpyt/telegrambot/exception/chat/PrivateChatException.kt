package io.github.dehuckakpyt.telegrambot.exception.chat

import org.jetbrains.annotations.ApiStatus.Experimental


/**
 * Created on 12.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Experimental
class PrivateChatException(message: String) : ChatException(message)