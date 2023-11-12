package yapp.study.todolist.domain.task.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.be
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.task.dto.TaskDetailDto
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
    val createInvalidIdRequest = TaskDto(
            id = 1,
            categoryId = 1,
            title = "2",
            memo = "22",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX,
            isDone = false
    )
    val createInvalidCategoryIdRequest = TaskDto(
            id = 1,
            categoryId = 2,
            title = "3",
            memo = "33",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX,
            isDone = false
    )
    val updateRequest = TaskDetailDto(
            categoryId = 1,
            title = "12",
            memo = "1122",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX,
    )
    val updateInvalidCategoryIdRequest = TaskDetailDto(
            categoryId = 2,
            title = "12",
            memo = "1122",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX,
    )
    val taskId: Long = 1
    val invalidTaskId: Long = 2
    val categoryId: Long = 1

    beforeEach {
        categoryRepository.save(Fixture.createCategory())
    }
    afterEach {
        taskRepository.deleteById(taskId)
        categoryRepository.deleteById(categoryId)
    }

    context("task 생성 테스트"){
        beforeEach {
            taskRepository.deleteById(taskId)
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
                taskService.createTask(createInvalidIdRequest)
            }
        }

        test("존재하지않는 카테고리의 task 생성시 실패"){
            // then
            shouldThrow<RuntimeException> {
                taskService.createTask(createInvalidCategoryIdRequest)
            }
        }
    }

    context("task 수정 테스트"){
        beforeEach {
            taskService.createTask(createRequest)
        }

        test("수정 성공"){
            // when
            taskService.updateTask(taskId, updateRequest)
            // then
            val task = taskRepository.findById(taskId)
            task shouldNotBe null
            task!!.id shouldBe 1
            task.categoryId shouldBe 1
            task.title shouldBe "12"
            task.memo shouldBe "1122"
            task.date shouldBe LocalDate.MIN
            task.fromTime shouldBe LocalTime.MIN
            task.toTime shouldBe LocalTime.MAX
            task.isDone shouldBe false
        }

        test("존재하지않는 task id일 경우 실패"){
            // then
            shouldThrow<RuntimeException> {
                taskService.updateTask(invalidTaskId, updateRequest)
            }
        }

        test("존재하지않는 카테고리 id일 경우 실패"){
            // then
            shouldThrow<RuntimeException> {
                taskService.updateTask(taskId, updateInvalidCategoryIdRequest)
            }
        }
    }

    context("task 삭제 테스트"){
        beforeEach {
            taskService.createTask(createRequest)
        }
        afterEach {
            taskService.createTask(createRequest)
        }

        test("삭제 성공"){
            // when
            taskService.deleteTask(taskId)
            // then
            taskRepository.findById(taskId) shouldBe null
        }

        test("존재하지않는 id일 경우 실패"){
            // when
            taskService.deleteTask(taskId)
            // then
            shouldThrow<RuntimeException> {
                taskService.deleteTask(taskId)
            }
        }
    }

    context("task 완료 상태 변경 테스트"){
        beforeEach {
            taskService.createTask(createRequest)
        }

        test("상태 완료로 변경 완료"){
            // when
            taskService.updateDoneTask(taskId, true)
            // then
            val task = taskRepository.findById(taskId)
            task shouldNotBe null
            task!!.isDone shouldBe true
        }

        test("상태 미완료로 변경 완료"){
            // when
            taskService.updateDoneTask(taskId, false)
            // then
            val task = taskRepository.findById(taskId)
            task shouldNotBe null
            task!!.isDone shouldBe false
        }

        test("존재하지않는 id일 경우 실패"){
            // then
            shouldThrow<RuntimeException> {
                taskService.updateDoneTask(invalidTaskId, false)
            }
        }
    }
})