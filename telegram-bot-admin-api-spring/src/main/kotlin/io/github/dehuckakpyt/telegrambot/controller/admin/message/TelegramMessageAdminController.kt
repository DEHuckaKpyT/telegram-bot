package io.github.dehuckakpyt.telegrambot.controller.admin.message

import io.github.dehuckakpyt.telegrambot.controller.admin.message.dto.FilterTelegramMessageAdminDto
import io.github.dehuckakpyt.telegrambot.controller.admin.message.dto.TelegramMessageAdminDto
import io.github.dehuckakpyt.telegrambot.controller.admin.message.dto.TelegramMessageAdminListDto
import io.github.dehuckakpyt.telegrambot.controller.common.dto.PageDto
import io.github.dehuckakpyt.telegrambot.controller.common.dto.SliceDto
import io.github.dehuckakpyt.telegrambot.ext.spring.data.domain.toPageDto
import io.github.dehuckakpyt.telegrambot.ext.spring.data.domain.toSliceDto
import io.github.dehuckakpyt.telegrambot.mapper.controller.admin.message.TelegramMessageAdminMapper
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.source.message.TelegramMessageAdminSource
import io.github.dehuckakpyt.telegrambot.source.message.argument.SimpleFilterTelegramMessageArgument
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


/**
 * Api's for admin-ui.
 *
 * @author Denis Matytsin
 */
@RestController
@RequestMapping("/admin/telegram-messages")
open class TelegramMessageAdminController(
    protected val telegramMessageService: TelegramMessageAdminSource<UUID, out TelegramMessage<UUID>, SimpleFilterTelegramMessageArgument>,
    protected val telegramMessageMapper: TelegramMessageAdminMapper,
) {

    @GetMapping("/{id}")
    open suspend fun get(@PathVariable id: UUID): TelegramMessageAdminDto =
        telegramMessageService.get(id)
            .let(telegramMessageMapper::toTelegramMessageAdminDto)

    @GetMapping("/slice")
    open suspend fun slice(filters: FilterTelegramMessageAdminDto, pageable: Pageable): SliceDto<TelegramMessageAdminListDto> =
        telegramMessageMapper.toFilterTelegramMessageArgument(filters)
            .let { filters -> telegramMessageService.slice(filters, pageable) }
            .toSliceDto(telegramMessageMapper::toTelegramMessageAdminListDto)

    @GetMapping("/page")
    open suspend fun page(filters: FilterTelegramMessageAdminDto, pageable: Pageable): PageDto<TelegramMessageAdminListDto> =
        telegramMessageMapper.toFilterTelegramMessageArgument(filters)
            .let { filters -> telegramMessageService.page(filters, pageable) }
            .toPageDto(telegramMessageMapper::toTelegramMessageAdminListDto)

    @GetMapping("/count")
    open suspend fun count(filters: FilterTelegramMessageAdminDto): Long =
        telegramMessageMapper.toFilterTelegramMessageArgument(filters)
            .let { telegramMessageService.count(it) }
}