package io.github.dehuckakpyt.telegrambot.ext.spring.data.domain

import io.github.dehuckakpyt.telegrambot.controller.common.dto.SliceDto
import org.springframework.data.domain.Slice


/**
 * @author Denis Matytsin
 */
public fun <T, R> Slice<T>.toSliceDto(transform: (T) -> R): SliceDto<R> = SliceDto(
    content = content.map(transform),
    hasNext = hasNext(),
)