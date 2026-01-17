package io.github.dehuckakpyt.telegrambot.mapper.controller.admin.user

import io.github.dehuckakpyt.telegrambot.controller.admin.user.dto.FilterTelegramUserAdminDto
import io.github.dehuckakpyt.telegrambot.controller.admin.user.dto.TelegramUserAdminDto
import io.github.dehuckakpyt.telegrambot.controller.admin.user.dto.TelegramUserAdminListDto
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.source.user.argument.SimpleFilterTelegramUserArgument
import org.mapstruct.Mapper
import java.util.*


/**
 * @author Denis Matytsin
 */
@Mapper
interface TelegramUserAdminMapper {

    fun toFilterTelegramUserArgument(filters: FilterTelegramUserAdminDto): SimpleFilterTelegramUserArgument

    fun toTelegramUserAdminDto(user: TelegramUser<UUID>): TelegramUserAdminDto

    fun toTelegramUserAdminListDto(user: TelegramUser<UUID>): TelegramUserAdminListDto
}