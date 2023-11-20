package yapp.study.todolist.domain.todo.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import yapp.study.todolist.domain.base.IdGenerator
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.todo.dto.TodoDetailDto
import yapp.study.todolist.domain.todo.repository.TodoRepository
import yapp.study.todolist.domain.testFixture.Fixture
import java.time.LocalDate
import java.time.LocalTime

@SpringBootTest
class TodoServiceTest @Autowired constructor(
        private val categoryRepository: CategoryRepository,
        private val todoService: TodoService,
        private val todoRepository: TodoRepository,
        private val commentRepository: CommentRepository,
        private val idGenerator: IdGenerator
):FunSpec({
    val categoryId: Long = 1
    val commentId: Long = 1
    val createRequest = TodoDetailDto(
            categoryId = categoryId,
            title = "1",
            memo = "11",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX
    )
    val createInvalidCategoryIdRequest = TodoDetailDto(
            categoryId = categoryId + 1,
            title = "3",
            memo = "33",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX
    )
    val updateRequest = TodoDetailDto(
            categoryId = 1,
            title = "12",
            memo = "1122",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX,
    )
    val updateInvalidCategoryIdRequest = TodoDetailDto(
            categoryId = 2,
            title = "12",
            memo = "1122",
            date = LocalDate.MIN,
            fromTime = LocalTime.MIN,
            toTime = LocalTime.MAX,
    )

    beforeEach {
        categoryRepository.save(Fixture.createCategory())
    }
    afterEach {
        todoRepository.deleteAll()
        categoryRepository.deleteAll()
    }

    context("todo 생성 테스트"){
        beforeEach {
            todoRepository.deleteAll()
        }

        test("생성 성공"){
            // when
            val todoId = todoService.createTodo(createRequest)
            // then
            val todo = todoRepository.findById(todoId)
            todo shouldNotBe null
            todo!!.id shouldBe todoId
            todo.categoryId shouldBe categoryId
            todo.title shouldBe "1"
            todo.memo shouldBe "11"
            todo.date shouldBe LocalDate.MIN
            todo.fromTime shouldBe LocalTime.MIN
            todo.toTime shouldBe LocalTime.MAX
            todo.isDone shouldBe false
        }

        test("존재하지않는 카테고리의 todo 생성시 실패"){
            // then
            shouldThrow<RuntimeException> {
                todoService.createTodo(createInvalidCategoryIdRequest)
            }
        }
    }

    context("todo 수정 테스트"){
        test("수정 성공"){
            // when
            val todoId = todoService.createTodo(createRequest)
            todoService.updateTodo(todoId, updateRequest)
            // then
            val todo = todoRepository.findById(todoId)
            todo shouldNotBe null
            todo!!.id shouldBe todoId
            todo.categoryId shouldBe 1
            todo.title shouldBe "12"
            todo.memo shouldBe "1122"
            todo.date shouldBe LocalDate.MIN
            todo.fromTime shouldBe LocalTime.MIN
            todo.toTime shouldBe LocalTime.MAX
            todo.isDone shouldBe false
        }

        test("존재하지않는 todo id일 경우 실패"){
            // then
            shouldThrow<RuntimeException> {
                todoService.updateTodo(idGenerator.getAndIncreaseTodoId(), updateRequest)
            }
        }

        test("존재하지않는 카테고리 id일 경우 실패"){
            val todoId = todoService.createTodo(createRequest)
            // then
            shouldThrow<RuntimeException> {
                todoService.updateTodo(todoId, updateInvalidCategoryIdRequest)
            }
        }
    }

    context("todo 삭제 테스트"){
        test("삭제 성공"){
            // when
            val todoId = todoService.createTodo(createRequest)
            commentRepository.save(Fixture.createComment(todoId = todoId))
            todoService.deleteTodo(todoId)
            // then
            todoRepository.findById(todoId) shouldBe null
            commentRepository.findById(commentId) shouldBe null
        }

        test("존재하지않는 id일 경우 실패"){
            // when
            val todoId = todoService.createTodo(createRequest)
            todoService.deleteTodo(todoId)
            // then
            shouldThrow<RuntimeException> {
                todoService.deleteTodo(todoId)
            }
        }
    }

    context("todo 완료 상태 변경 테스트"){

        test("상태 완료로 변경 완료"){
            // when
            val todoId = todoService.createTodo(createRequest)
            todoService.updateDoneTodo(todoId, true)
            // then
            val todo = todoRepository.findById(todoId)
            todo shouldNotBe null
            todo!!.isDone shouldBe true
        }

        test("상태 미완료로 변경 완료"){
            // when
            val todoId = todoService.createTodo(createRequest)
            todoService.updateDoneTodo(todoId, true)
            todoService.updateDoneTodo(todoId, false)
            // then
            val todo = todoRepository.findById(todoId)
            todo shouldNotBe null
            todo!!.isDone shouldBe false
        }

        test("존재하지않는 id일 경우 실패"){
            // then
            shouldThrow<RuntimeException> {
                todoService.updateDoneTodo(idGenerator.getAndIncreaseTodoId(), false)
            }
        }
    }
})