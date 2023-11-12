package yapp.study.todolist.domain.task.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.task.dto.TaskDto
import yapp.study.todolist.domain.task.repository.TaskRepository
import yapp.study.todolist.domain.testFixture.Fixture
import java.time.LocalDate
import java.time.LocalTime

@SpringBootTest
class TaskServiceTest @Autowired constructor(
        private val categoryRepository: CategoryRepository,
        private val taskService: TaskService,
        private val taskRepository: TaskRepository
):FunSpec({
    val createRequest = TaskDto(
            id = 1,
            categoryId = 1,
            title = "1",
            memo = "11",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX,
            isDone = false
    )
    val createInvalidRequest = TaskDto(
            id = 1,
            categoryId = 1,
            title = "2",
            memo = "22",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX,
            isDone = false
    )
    val createInvalidRequest2 = TaskDto(
            id = 1,
            categoryId = 2,
            title = "3",
            memo = "33",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX,
            isDone = false
    )
    val taskId: Long = 1

    beforeEach {
        categoryRepository.save(Fixture.createCategory())
    }
    afterEach {
        taskRepository.deleteById(1)
    }

    context("task 생성 테스트"){
        beforeEach {
            taskRepository.deleteById(1)
        }

        test("생성 성공"){
            // when
            taskService.createTask(createRequest)
            // then
            val task = taskRepository.findById(taskId)
            task shouldNotBe null
            task!!.id shouldBe 1
            task.categoryId shouldBe 1
            task.title shouldBe "1"
            task.memo shouldBe "11"
            task.date shouldBe LocalDate.MIN
            task.fromTime shouldBe LocalTime.MIN
            task.toTime shouldBe LocalTime.MAX
            task.isDone shouldBe false
        }

        test("id 중복시 생성 실패"){
            // when
            taskService.createTask(createRequest)
            // then
            shouldThrow<RuntimeException> {
                taskService.createTask(createInvalidRequest)
            }
        }

        test("존재하지않는 카테고리의 task 생성시 실패"){
            // then
            shouldThrow<RuntimeException> {
                taskService.createTask(createInvalidRequest2)
            }
        }
    }

    context("task 수정 테스트"){
        test("수정 성공"){
            // given
            // when
            // then
        }

        test("존재하지않는 id일 경우 실패"){
            // given
            // when
            // then
        }
    }

    context("task 삭제 테스트"){
        test("삭제 성공"){
            // given
            // when
            // then
        }

        test("존재하지않는 id일 경우 실패"){
            // given
            // when
            // then
        }
    }

    context("task 완료 상태 변경 테스트"){
        test("상태 변경 완료"){
            // given
            // when
            // then
        }

        test("존재하지않는 id일 경우 실패"){
            // given
            // when
            // then
        }
    }
})