package io.github.dehuckakpyt.telegrambot.source.base

import io.github.dehuckakpyt.telegrambot.ext.kotlin.reflect.firstGenericClass
import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.repository.base.TelegramEntityRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.jetbrains.annotations.ApiStatus.Experimental
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import java.util.*


/**
 * @author Denis Matytsin
 */
@Experimental
abstract class ReadTelegramEntitySource<EntityT : UUIDTable, FilterArgumentT : Any> {

    protected abstract val transactional: TransactionAction
    protected abstract val repository: TelegramEntityRepository<EntityT>
    protected abstract val entityManager: EntityManager

    protected val entityClass: Class<EntityT> = this::class.firstGenericClass()
    protected lateinit var criteriaBuilder: CriteriaBuilder
    protected lateinit var criteriaQuery: CriteriaQuery<EntityT>
    protected lateinit var root: Root<EntityT>

    @PostConstruct
    fun postConstruct() {
        // Overwise NPE when entityManager.getCriteriaBuilder()
        criteriaBuilder = entityManager.criteriaBuilder
        criteriaQuery = criteriaBuilder.createQuery(entityClass)
        root = criteriaQuery.from(entityClass)
    }

    suspend fun get(id: UUID): EntityT = transactional(readOnly = true) {
        repository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Telegram user with given id '$id' does not exist.")
    }

    suspend fun page(arg: FilterArgumentT, pageable: Pageable): Page<EntityT> = transactional(readOnly = true) {
        repository.findAll(arg.toSpecification(), pageable)
    }

    suspend fun slice(arg: FilterArgumentT, pageable: Pageable): Slice<EntityT> = transactional(readOnly = true) {
        arg.toSpecification()
            ?.toPredicate(root, criteriaQuery, criteriaBuilder)
            ?.let(criteriaQuery::where)

        // sort from Pageable
        if (pageable.sort.isSorted) {
            val orders = pageable.sort.mapTo(mutableListOf()) { order ->
                if (order.isAscending) criteriaBuilder.asc(root.get<Any>(order.property))
                else criteriaBuilder.desc(root.get<Any>(order.property))
            }
            criteriaQuery.orderBy(orders)
        }

        val query = entityManager.createQuery(criteriaQuery)
        query.firstResult = pageable.offset.toInt()
        query.maxResults = pageable.pageSize + 1 // +1 to know hasNext

        var resultList = query.resultList
        val hasNext = resultList.size > pageable.pageSize
        if (hasNext) {
            resultList = resultList.dropLast(1)
        }

        SliceImpl(resultList, pageable, hasNext)
    }

    suspend fun count(arg: FilterArgumentT): Long = transactional(readOnly = true) {
        repository.count(arg.toSpecification())
    }

    protected open fun FilterArgumentT.toSpecification(): Specification<EntityT>? = Specification { root, query, builder ->
        val predicates = toPredicates(root, query, builder)

        if (predicates.isNullOrEmpty()) null
        else builder.and(*predicates.toTypedArray())
    }

    protected open fun FilterArgumentT.toPredicates(root: Root<EntityT>, query: CriteriaQuery<*>?, builder: CriteriaBuilder): List<Predicate>? = emptyList()
}