package yapp.study.todolist.domain.category.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import yapp.study.todolist.domain.category.dto.CategoryNameDto
import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.testFixture.Fixture
import yapp.study.todolist.domain.todo.entity.Todo
import yapp.study.todolist.domain.todo.repository.TodoRepository

@ExtendWith(MockKExtension::class)
internal class CategoryServiceTest: FunSpec({
    val categoryRepository = mockk<CategoryRepository>()
    val todoRepository = mockk<TodoRepository>()
    val commentRepository = mockk<CommentRepository>()

    val categoryService = CategoryService(categoryRepository, todoRepository, commentRepository)
    val categoryId: Long = 1
    val invalidCategoryId: Long = 1
    val createRequest = CategoryNameDto(
            name = "1111"
    )
    val updateRequest = CategoryNameDto(
            name = "11112222"
    )
    val category = Fixture.createCategory()

    context("카테고리 생성 테스트"){
        test("생성 성공"){
            // given
            every { categoryRepository.save(any()) } returns category
            // when
            val categoryId = categoryService.createCategory(createRequest)
            // then
            categoryId shouldBe categoryId
        }
    }

    context("카테고리 수정 테스트"){
        test("수정 성공"){
            // given
            every { categoryRepository.findByIdOrNull(categoryId) } returns category
            // when
            categoryService.updateCategory(categoryId, updateRequest)
        }

        test("해당 id의 카테고리가 없을 경우 실패"){
            // given
            every { categoryRepository.findByIdOrNull(categoryId) } returns null
            // then
            shouldThrow<RuntimeException> {
                categoryService.updateCategory(invalidCategoryId, updateRequest)
            }
        }
    }

    context("카테고리 삭제 테스트"){
        test("삭제 성공"){
            // given
            every { categoryRepository.findByIdOrNull(categoryId) } returns category
            every { categoryRepository.deleteById(categoryId) } returns Unit
            every { todoRepository.findByCategoryId(categoryId) } returns emptyList()
            every { commentRepository.deleteAllByTodoIdIn(any()) } returns Unit
            every { todoRepository.deleteAllByIdIn(any()) } returns Unit
            // when
            categoryService.deleteCategory(categoryId)
        }

        test("해당 id의 카테고리가 없을 경우 실패"){
            // given
            every { categoryRepository.findByIdOrNull(categoryId) } returns null
            // then
            shouldThrow<RuntimeException> {
                categoryService.deleteCategory(categoryId)
            }
        }
    }
})