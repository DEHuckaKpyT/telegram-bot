package io.github.dehuckakpyt.telegrambot.controller.common.dto


/**
 * @author Denis Matytsin
 */
data class PageDto<T>(
    val content: List<T>,
    val totalPages: Int,
    val totalElements: Long,
)
