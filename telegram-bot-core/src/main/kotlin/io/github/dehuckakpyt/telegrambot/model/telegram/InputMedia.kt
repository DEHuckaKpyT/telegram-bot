package io.github.dehuckakpyt.telegrambot.model.telegram

import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * This object represents the content of a media message to be sent. It should be one of
 *
 * * [InputMediaAnimation](https://core.telegram.org/bots/api/#inputmediaanimation)
 * * [InputMediaDocument](https://core.telegram.org/bots/api/#inputmediadocument)
 * * [InputMediaAudio](https://core.telegram.org/bots/api/#inputmediaaudio)
 * * [InputMediaPhoto](https://core.telegram.org/bots/api/#inputmediaphoto)
 * * [InputMediaVideo](https://core.telegram.org/bots/api/#inputmediavideo)
 *
 * @see [InputMedia] (https://core.telegram.org/bots/api/#inputmedia)
 *
 * @author KScript
 */
public sealed interface InputMedia {
    public val type: String

    public val media: Input

    public val caption: String?

    public val parseMode: String?

    public val captionEntities: List<MessageEntity>?

    public val thumbnail: Input?
}
