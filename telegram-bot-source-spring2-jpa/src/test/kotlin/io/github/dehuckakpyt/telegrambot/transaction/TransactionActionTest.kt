package io.github.dehuckakpyt.telegrambot.transaction

import io.github.dehuckakpyt.telegrambot.test.EnablePostgresTestContainer
import io.github.dehuckakpyt.telegrambot.test.createDataSet
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.string.shouldContain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.Isolation.SERIALIZABLE
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.AUTO
import javax.persistence.Id

@SpringBootTest
@EnablePostgresTestContainer
class TransactionActionTest @Autowired constructor(
    private val transactional: TransactionAction,
    private val repository: TestEntityRepository,
) : FreeSpec({

    "simple transaction" {
        // Arrange
        createDataSet()

        // Act
        val entity: TestEntity = transactional {
            repository.save(TestEntity("name", 1))
        }

        // Assert
        assertSoftly {
            entity.name shouldBeEqual "name"
            entity.value shouldBeEqual 1

            repository.count() shouldBeEqual 1
        }
    }

    "transaction rollback with manually throwing exception" {
        // Arrange
        createDataSet()

        // Act
        val exception = shouldThrow<RuntimeException> {
            transactional {
                repository.save(TestEntity("name", 1))
                throw RuntimeException("unexpected exception")
            }
        }

        // Assert
        assertSoftly {
            exception.message!! shouldBeEqual "unexpected exception"

            repository.count() shouldBeEqual 0
        }
    }

    "transaction rollback with database exception" {
        // Arrange
        createDataSet()

        // Act
        val exception = shouldThrow<DataIntegrityViolationException> {
            transactional {
                repository.save(TestEntity("name 1", 1))
                repository.save(TestEntity("name 2", 1))
            }
        }

        // Assert
        assertSoftly {
            exception.stackTraceToString() shouldContain "org.postgresql.util.PSQLException: ERROR: duplicate key value violates unique constraint"

            repository.count() shouldBeEqual 0
        }
    }

    "transaction returns Unit" {
        // Arrange
        createDataSet()

        // Act
        val entity = transactional {
            repository.save(TestEntity("name", 1))
        }
        val countBeforeDelete = repository.count()

        val unit: Unit = transactional(isolation = SERIALIZABLE) {
            repository.delete(entity)
        }
        val countAfterDelete = repository.count()

        // Assert
        assertSoftly {
            countBeforeDelete shouldBeEqual 1
            countAfterDelete shouldBeEqual 0
        }
    }
}) {

    @EntityScan(basePackageClasses = [TestEntity::class])
    @EnableJpaRepositories(basePackageClasses = [TestEntityRepository::class])
    @SpringBootApplication
    class TestConfig
}

@Entity
class TestEntity(

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val value: Int,
) {

    @Id
    @GeneratedValue(strategy = AUTO)
    var id: UUID? = null
}

public interface TestEntityRepository : JpaRepository<TestEntity, UUID>

