package yapp.study.todolist.common.adaptor

import io.kotest.core.spec.style.FunSpec
import org.assertj.core.api.AssertionsForClassTypes
import org.springframework.stereotype.Service
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong

internal class SyncAdaptorTest: FunSpec({
    val testService = TestService()

    context("count 감소 test"){
        test("count가 정상적으로 감소해야한다."){
            // when
            val successCount = AtomicLong()
            executeMultiThread(
                { syncAdaptor { testService.decreaseStock(1) } },
                successCount,
            )
            // then
            val remain = 100 - successCount.toInt()
            AssertionsForClassTypes.assertThat(testService.stock).isEqualTo(remain)
        }
    }
})

@Service
class TestService(
    var stock: Int = 100,
) {
    fun decreaseStock(id: Int) {
        stock -= 1
    }
}

fun executeMultiThread(operation: () -> Unit, successCount: AtomicLong) {
    val executorService = Executors.newFixedThreadPool(5)
    val latch = CountDownLatch(30)
    for (i in 1..30) {
        executorService.submit {
            try {
                operation()
                successCount.getAndIncrement()
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