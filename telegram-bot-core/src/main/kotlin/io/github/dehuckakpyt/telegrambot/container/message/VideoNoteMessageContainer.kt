package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.VideoNote


/**
 * Created on 09.12.2024.
 *
 * @author Denis Matytsin
 */
class VideoNoteMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val videoNote: VideoNote get() = message.videoNote!!

    override val type: String = "VIDEO_NOTE"
}