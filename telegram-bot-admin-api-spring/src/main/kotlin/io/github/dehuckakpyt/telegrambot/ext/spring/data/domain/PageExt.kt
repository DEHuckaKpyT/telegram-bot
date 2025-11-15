package io.github.dehuckakpyt.telegrambot.ext.spring.data.domain

import io.github.dehuckakpyt.telegrambot.controller.common.dto.PageDto
import org.springframework.data.domain.Page


/**
 * @author Denis Matytsin
 */
public fun <T, R> Page<T>.toPageDto(transform: (T) -> R): PageDto<R> = PageDto(
    content = content.map(transform),
    totalElements = totalElements,
    totalPages = totalPages,
)