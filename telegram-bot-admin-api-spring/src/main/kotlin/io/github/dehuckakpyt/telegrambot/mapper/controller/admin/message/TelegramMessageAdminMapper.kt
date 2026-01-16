package io.github.dehuckakpyt.telegrambot.mapper.controller.admin.message

import io.github.dehuckakpyt.telegrambot.controller.admin.message.dto.FilterTelegramMessageAdminDto
import io.github.dehuckakpyt.telegrambot.controller.admin.message.dto.TelegramMessageAdminDto
import io.github.dehuckakpyt.telegrambot.controller.admin.message.dto.TelegramMessageAdminListDto
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.source.message.argument.FilterTelegramMessageArgument
import org.mapstruct.Mapper
import java.util.*


/**
 * @author Denis Matytsin
 */
@Mapper
interface TelegramMessageAdminMapper {

    fun toFilterTelegramMessageArgument(filters: FilterTelegramMessageAdminDto): FilterTelegramMessageArgument

    fun toTelegramMessageAdminDto(message: TelegramMessage<UUID>): TelegramMessageAdminDto

    fun toTelegramMessageAdminListDto(message: TelegramMessage<UUID>): TelegramMessageAdminListDto
}