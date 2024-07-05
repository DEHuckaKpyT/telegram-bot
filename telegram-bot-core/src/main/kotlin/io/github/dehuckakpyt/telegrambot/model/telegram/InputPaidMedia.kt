package io.github.dehuckakpyt.telegrambot.model.telegram

import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import kotlin.String

/**
 * This object describes the paid media to be sent. Currently, it can be one of
 *
 * * [InputPaidMediaPhoto](https://core.telegram.org/bots/api/#inputpaidmediaphoto)
 * * [InputPaidMediaVideo](https://core.telegram.org/bots/api/#inputpaidmediavideo)
 *
 * @see [InputPaidMedia] (https://core.telegram.org/bots/api/#inputpaidmedia)
 *
 * @author KScript
 */
public sealed interface InputPaidMedia {
    public val type: String

    public val media: Input

    public val thumbnail: Input?
}
