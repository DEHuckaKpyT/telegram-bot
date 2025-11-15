package io.github.dehuckakpyt.telegrambot.controller.common.dto


/**
 * @author Denis Matytsin
 */
data class SliceDto<T>(
    val content: List<T>,
    val hasNext: Boolean,
)
