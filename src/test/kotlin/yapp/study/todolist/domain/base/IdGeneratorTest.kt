package yapp.study.todolist.domain.base

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong

internal class IdGeneratorTest: FunSpec({
    val idGenerator = IdGenerator()

    context("id 생성 테스트") {
        test("category id는 중복 생성되지 않는다.") {
            val successCount = AtomicLong()
            executeMultiThread({ idGenerator.getAndIncreaseCategoryId() }, successCount)
            successCount.toInt() shouldBe 30
        }

        test("todo id는 중복 생성되지 않는다.") {
            val successCount = AtomicLong()
            executeMultiThread({ idGenerator.getAndIncreaseTodoId() }, successCount)
            successCount.toInt() shouldBe 30
        }


        test("comment id는 중복 생성되지 않는다.") {
            val successCount = AtomicLong()
            executeMultiThread({ idGenerator.getAndIncreaseCommentId() }, successCount)
            successCount.toInt() shouldBe 30
        }
    }
})

fun executeMultiThread(operation: () -> Any?, successCount: AtomicLong) {
    val executorService = Executors.newFixedThreadPool(5)
    val latch = CountDownLatch(30)
    val idList = emptyList<Long>()
    for (i in 1..30) {
        executorService.submit {
            try {
                val generatedId = operation()
                println(generatedId)
                if (!idList.contains(generatedId)) {
                    successCount.getAndIncrement()
                    idList.plus(generatedId)
                }
            }
            catch(e: Throwable) {
                println(e)
            } finally {
                latch.countDown()
            }
        }
    }
    latch.await()
}