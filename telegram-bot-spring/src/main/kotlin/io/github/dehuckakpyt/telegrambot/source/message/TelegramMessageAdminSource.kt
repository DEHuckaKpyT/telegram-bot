package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.source.message.argument.FilterTelegramMessageArgument
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice


/**
 * @author Denis Matytsin
 */
interface TelegramMessageAdminSource<EntityIdT : Any, EntityT : TelegramMessage<EntityIdT>> {

    /**
     * Get telegram user by id.
     *
     * @param id of entity
     *
     * @return TelegramUser entity
     */
    public suspend fun get(id: EntityIdT): EntityT

    /**
     * Get telegram users page.
     *
     * @param arg filters for search users (applying by 'and')
     * @param pageable page params
     *
     * @return page of TelegramUser entities
     */
    public suspend fun page(arg: FilterTelegramMessageArgument, pageable: Pageable): Page<EntityT>

    /**
     * Get telegram users slice.
     *
     * @param arg filters for search users (applying by 'and')
     * @param pageable page params
     *
     * @return slice of TelegramUser entities
     */
    public suspend fun slice(arg: FilterTelegramMessageArgument, pageable: Pageable): Slice<EntityT>

    /**
     * Get telegram users slice.
     *
     * @param arg filters for search users (applying by 'and')
     *
     * @return count of TelegramUser entities
     */
    public suspend fun count(arg: FilterTelegramMessageArgument): Long

    companion object
}