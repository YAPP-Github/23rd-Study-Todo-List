package yapp.study.todolist.domain.todo.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import yapp.study.todolist.domain.base.IdGenerator
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.todo.dto.TodoDetailDto
import yapp.study.todolist.domain.todo.repository.TodoRepository
import yapp.study.todolist.domain.testFixture.Fixture
import yapp.study.todolist.domain.todo.entity.Todo
import java.time.LocalDate
import java.time.LocalTime

@SpringBootTest
class TodoServiceTest:FunSpec ({
    val categoryRepository = mockk<CategoryRepository>()
    val todoRepository = mockk<TodoRepository>()
    val commentRepository = mockk<CommentRepository>()

    val todoService = TodoService(categoryRepository, todoRepository, commentRepository)
    val categoryId: Long = 1
    val invalidCategoryId: Long = 2
    val todoId: Long = 1
    val invalidTodoId: Long = 2
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
    val todo = Fixture.createTodo()

    every { todoRepository.save(any()) } returns todo
    context("todo 생성 테스트"){
        test("생성 성공"){
            // given
            every { categoryRepository.existsById(categoryId) } returns true
            // when
            val todoId = todoService.createTodo(createRequest)
            // then
            todoId shouldBe todoId
        }

        test("존재하지않는 카테고리의 todo 생성시 실패"){
            // given
            every { categoryRepository.existsById(invalidCategoryId) } returns false
            // then
            shouldThrow<RuntimeException> {
                todoService.createTodo(createInvalidCategoryIdRequest)
            }
        }
    }

    context("todo 수정 테스트"){
        test("수정 성공"){
            // given
            every { categoryRepository.existsById(categoryId) } returns true
            every { todoRepository.findByIdOrNull(todoId) } returns Todo.toEntity(createRequest)
            // when
            todoService.updateTodo(todoId, updateRequest)
        }

        test("존재하지않는 todo id일 경우 실패"){
            // given
            every { categoryRepository.existsById(categoryId) } returns true
            every { todoRepository.findByIdOrNull(invalidTodoId) } returns null
            // then
            shouldThrow<RuntimeException> {
                todoService.updateTodo(invalidTodoId, updateRequest)
            }
        }

        test("존재하지않는 카테고리 id일 경우 실패"){
            // given
            every { categoryRepository.existsById(invalidCategoryId) } returns false
            // then
            shouldThrow<RuntimeException> {
                todoService.updateTodo(todoId, updateInvalidCategoryIdRequest)
            }
        }
    }

    context("todo 삭제 테스트"){
        test("삭제 성공"){
            // given
            every { todoRepository.existsById(todoId) } returns true
            every { todoRepository.deleteById(any()) } returns Unit
            every { commentRepository.deleteAllByTodoId(any()) } returns Unit
            // when
            todoService.deleteTodo(todoId)
        }

        test("존재하지않는 id일 경우 실패"){
            // when
            every { todoRepository.existsById(invalidTodoId) } returns false
            // then
            shouldThrow<RuntimeException> {
                todoService.deleteTodo(invalidTodoId)
            }
        }
    }

    context("todo 완료 상태 변경 테스트"){

        test("상태 완료로 변경 완료"){
            // given
            every { todoRepository.findByIdAndIsDone(todoId, false) } returns Todo.toEntity(createRequest)
            // when
            todoService.updateDoneTodo(todoId, true)
        }

        test("상태 미완료로 변경 완료"){
            // given
            every { todoRepository.findByIdAndIsDone(todoId, true) } returns Todo.toEntity(createRequest)
            // when
            todoService.updateDoneTodo(todoId, false)
        }

        test("존재하지않는 id일 경우 실패"){
            // given
            every { todoRepository.findByIdAndIsDone(todoId, true) } returns null
            // then
            shouldThrow<RuntimeException> {
                todoService.updateDoneTodo(invalidTodoId, false)
            }
        }
    }
})