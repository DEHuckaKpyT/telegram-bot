package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.source.message.argument.SimpleFilterTelegramMessageArgument
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice


/**
 * @author Denis Matytsin
 */
interface TelegramMessageAdminSource<EntityIdT : Any, EntityT : TelegramMessage<EntityIdT>, FilterArgumentT : SimpleFilterTelegramMessageArgument> {

    /**
     * Get telegram message by id.
     *
     * @param id of entity
     *
     * @return TelegramMessage entity
     */
    public suspend fun get(id: EntityIdT): EntityT

    /**
     * Get telegram messages page.
     *
     * @param arg filters for search messages (applying by 'and')
     * @param pageable page params
     *
     * @return page of TelegramMessage entities
     */
    public suspend fun page(arg: FilterArgumentT, pageable: Pageable): Page<EntityT>

    /**
     * Get telegram messages slice.
     *
     * @param arg filters for search messages (applying by 'and')
     * @param pageable page params
     *
     * @return slice of TelegramMessage entities
     */
    public suspend fun slice(arg: FilterArgumentT, pageable: Pageable): Slice<EntityT>

    /**
     * Get telegram messages slice.
     *
     * @param arg filters for search messages (applying by 'and')
     *
     * @return count of TelegramMessage entities
     */
    public suspend fun count(arg: FilterArgumentT): Long

    companion object
}