package io.github.dehuckakpyt.telegrambot.controller.admin.user

import io.github.dehuckakpyt.telegrambot.controller.admin.user.dto.FilterTelegramUserAdminDto
import io.github.dehuckakpyt.telegrambot.controller.admin.user.dto.TelegramUserAdminDto
import io.github.dehuckakpyt.telegrambot.controller.admin.user.dto.TelegramUserAdminListDto
import io.github.dehuckakpyt.telegrambot.controller.common.dto.PageDto
import io.github.dehuckakpyt.telegrambot.controller.common.dto.SliceDto
import io.github.dehuckakpyt.telegrambot.ext.spring.data.domain.toPageDto
import io.github.dehuckakpyt.telegrambot.ext.spring.data.domain.toSliceDto
import io.github.dehuckakpyt.telegrambot.mapper.controller.admin.user.TelegramUserAdminMapper
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserAdminSource
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
@RequestMapping("/admin/telegram-users")
open class TelegramUserAdminController(
    protected val telegramUserService: TelegramUserAdminSource<UUID, out TelegramUser<UUID>>,
    protected val telegramUserMapper: TelegramUserAdminMapper,
) {

    @GetMapping("/{id}")
    open suspend fun get(@PathVariable id: UUID): TelegramUserAdminDto =
        telegramUserService.get(id)
            .let(telegramUserMapper::toTelegramUserAdminDto)

    @GetMapping("/slice")
    open suspend fun slice(filters: FilterTelegramUserAdminDto, pageable: Pageable): SliceDto<TelegramUserAdminListDto> =
        telegramUserMapper.toFilterTelegramUserArgument(filters)
            .let { filters -> telegramUserService.slice(filters, pageable) }
            .toSliceDto(telegramUserMapper::toTelegramUserAdminListDto)

    @GetMapping("/page")
    open suspend fun page(filters: FilterTelegramUserAdminDto, pageable: Pageable): PageDto<TelegramUserAdminListDto> =
        telegramUserMapper.toFilterTelegramUserArgument(filters)
            .let { filters -> telegramUserService.page(filters, pageable) }
            .toPageDto(telegramUserMapper::toTelegramUserAdminListDto)

    @GetMapping("/count")
    open suspend fun count(filters: FilterTelegramUserAdminDto): Long =
        telegramUserMapper.toFilterTelegramUserArgument(filters)
            .let { telegramUserService.count(it) }
}