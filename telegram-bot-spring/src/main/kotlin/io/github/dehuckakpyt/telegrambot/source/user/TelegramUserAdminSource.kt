package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.source.user.argument.FilterTelegramUserArgument
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

/**
 * Source for show and manage users in admin-ui.
 *
 * @author Denis Matytsin
 */
public interface TelegramUserAdminSource<EntityIdT : Any, EntityT : TelegramUser<EntityIdT>> {

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
    public suspend fun page(arg: FilterTelegramUserArgument, pageable: Pageable): Page<EntityT>

    /**
     * Get telegram users slice.
     *
     * @param arg filters for search users (applying by 'and')
     * @param pageable page params
     *
     * @return slice of TelegramUser entities
     */
    public suspend fun slice(arg: FilterTelegramUserArgument, pageable: Pageable): Slice<EntityT>

    /**
     * Get telegram users slice.
     *
     * @param arg filters for search users (applying by 'and')
     *
     * @return count of TelegramUser entities
     */
    public suspend fun count(arg: FilterTelegramUserArgument): Long

    companion object
}