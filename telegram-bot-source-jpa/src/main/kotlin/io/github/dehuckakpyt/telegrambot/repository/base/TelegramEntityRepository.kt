package io.github.dehuckakpyt.telegrambot.repository.base

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean
import java.util.*


/**
 * @author Denis Matytsin
 */
@NoRepositoryBean
interface TelegramEntityRepository<EntityT : UUIDTable> : JpaRepository<EntityT, UUID>, JpaSpecificationExecutor<EntityT>
