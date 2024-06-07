package io.github.dehuckakpyt.telegrambot.sync

import io.github.dehuckakpyt.telegrambot.ext.sync.ASync
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.longs.shouldBeBetween
import io.mockk.*
import kotlinx.coroutines.delay
import java.lang.System.currentTimeMillis

class ActionSynchronizerTest : FreeSpec({

    val aSync = ASync<String>()

    // disabled because takes 6 secs
    "multiple invokes at one time".config(enabled = false) {
        // Arrange
        val invokedMillisecondsTask1 = mutableListOf<Long>()
        val invokedMillisecondsTask2 = mutableListOf<Long>()
        val invokedMillisecondsTask3 = mutableListOf<Long>()

        val task1 = mockk<suspend () -> Unit>()
        val task2 = mockk<suspend () -> Unit>()
        val task3 = mockk<suspend () -> Unit>()
        val exceptionTask1 = mockk<suspend () -> Unit>()
        val trigger1 = mockk<() -> Unit>(relaxed = true)
        val trigger2 = mockk<() -> Unit>(relaxed = true)
        val trigger3 = mockk<() -> Unit>(relaxed = true)
        val exceptionTrigger1 = mockk<() -> Unit>(relaxed = true)

        coEvery { task1() } coAnswers {
            delay(1000)
            invokedMillisecondsTask1.add(currentTimeMillis())
            trigger1()
        }
        coEvery { task2() } coAnswers {
            delay(1000)
            invokedMillisecondsTask2.add(currentTimeMillis())
            trigger2()
        }
        coEvery { task3() } coAnswers {
            delay(1000)
            invokedMillisecondsTask3.add(currentTimeMillis())
            trigger3()
        }
        coEvery { exceptionTask1() } coAnswers {
            delay(1000)
            invokedMillisecondsTask1.add(currentTimeMillis())
            exceptionTrigger1()
            throw RuntimeException("test error")
        }
        val startMilliseconds = currentTimeMillis()

        // Act
        aSync.parallelisedBy("1", task1)
        aSync.parallelisedBy("1", task1)
        aSync.parallelisedBy("2", task2)
        aSync.parallelisedBy("1", exceptionTask1)
        aSync.parallelisedBy("1", task1)
        aSync.parallelisedBy("2", task2)
        aSync.parallelisedBy("1", exceptionTask1)
        aSync.parallelisedBy("2", task2)
        aSync.parallelisedBy("3", task3)
        aSync.parallelisedBy("1", task1)

        // Assert
        coVerify(exactly = 4, timeout = 6100) { trigger1() }
        coVerify(exactly = 2) { exceptionTrigger1() }
        coVerify(exactly = 3) { trigger2() }
        coVerify(exactly = 1) { trigger3() }

        assertSoftly {
            invokedMillisecondsTask1 shouldHaveSize 6
            invokedMillisecondsTask2 shouldHaveSize 3
            invokedMillisecondsTask3 shouldHaveSize 1

            (invokedMillisecondsTask1[0] - startMilliseconds).shouldBeBetween(1000, 1050)
            (invokedMillisecondsTask1[1] - startMilliseconds).shouldBeBetween(2000, 2050)
            (invokedMillisecondsTask1[2] - startMilliseconds).shouldBeBetween(3000, 3075)
            (invokedMillisecondsTask1[3] - startMilliseconds).shouldBeBetween(4000, 4100)
            (invokedMillisecondsTask1[4] - startMilliseconds).shouldBeBetween(5000, 5100)
            (invokedMillisecondsTask1[5] - startMilliseconds).shouldBeBetween(6000, 6125)

            (invokedMillisecondsTask2[0] - startMilliseconds).shouldBeBetween(1000, 1050)
            (invokedMillisecondsTask2[1] - startMilliseconds).shouldBeBetween(2000, 2050)
            (invokedMillisecondsTask2[2] - startMilliseconds).shouldBeBetween(3000, 3075)

            (invokedMillisecondsTask3[0] - startMilliseconds).shouldBeBetween(1000, 1050)
        }
    }

    "invokes in different time" {
        // Arrange
        val task = mockk<suspend () -> Unit>()
        coEvery { task() } just runs

        // Act
        aSync.parallelisedBy("1", task)
        delay(10)
        aSync.parallelisedBy("2", task)
        delay(10)
        aSync.parallelisedBy("1", task)

        // Assert
        coVerify(exactly = 3) { task() }
    }

    "invokes in hard order" {
        // Arrange
        val task1 = mockk<suspend () -> Unit>()
        val task2 = mockk<suspend () -> Unit>()
        val task3 = mockk<suspend () -> Unit>()
        val task4 = mockk<suspend () -> Unit>()
        coEvery { task1() } just runs
        coEvery { task2() } just runs
        coEvery { task3() } just runs
        coEvery { task4() } just runs

        // Act
        aSync.parallelisedBy("1", task1)
        aSync.parallelisedBy("1", task2)
        aSync.parallelisedBy("1", task3)
        aSync.parallelisedBy("1", task4)

        // Assert
        coVerifySequence {
            task1()
            task2()
            task3()
            task4()
        }
    }

    "a lot of invokes" {
        // Arrange
        val oneItemList = mutableListOf(0)
        val task: suspend () -> Unit = {
            oneItemList[0] = oneItemList[0] + 1
        }

        // Act
        repeat(1000) {
            aSync.parallelisedBy("1", task)
        }

        // Assert
        delay(5) // in single test delay(1)
        oneItemList[0] shouldBeEqual 1000
    }
})
