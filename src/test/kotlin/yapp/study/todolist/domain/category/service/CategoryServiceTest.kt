package yapp.study.todolist.domain.category.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import yapp.study.todolist.domain.category.dto.CategoryNameDto
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.todo.repository.TodoRepository
import yapp.study.todolist.domain.testFixture.Fixture

@SpringBootTest
class CategoryServiceTest @Autowired constructor(
        private val categoryService: CategoryService,
        private val categoryRepository: CategoryRepository,
        private val todoRepository: TodoRepository,
        private val commentRepository: CommentRepository
) : FunSpec({
    val createRequest = CategoryNameDto(
            name = "1111"
    )


    context("카테고리 생성 테스트"){
        afterEach {
            categoryRepository.deleteAll()
        }

        test("생성 성공"){
            // when
            val categoryId = categoryService.createCategory(createRequest)
            // then
            val category = categoryRepository.findById(categoryId)
            category shouldNotBe null
            category!!.id shouldBe categoryId
            category.name shouldBe "1111"
        }
    }

    context("카테고리 수정 테스트"){
        afterEach {
            categoryRepository.deleteAll()
        }

        val updateRequest = CategoryNameDto(
                name = "11112222"
        )

        test("수정 성공"){
            // when
            val categoryId = categoryService.createCategory(createRequest)
            categoryService.updateCategory(categoryId, updateRequest)
            // then
            val category = categoryRepository.findById(categoryId)
            category shouldNotBe null
            category!!.id shouldBe categoryId
            category.name shouldBe "11112222"
        }

        test("해당 id의 카테고리가 없을 경우 실패"){
            // when
            val categoryId = categoryService.createCategory(createRequest)
            // then
            shouldThrow<RuntimeException> {
                categoryService.updateCategory(categoryId + 1, updateRequest)
            }
        }
    }

    context("카테고리 삭제 테스트"){
        var categoryId: Long = 1
        beforeEach {
            categoryId = categoryService.createCategory(createRequest)
            todoRepository.save(Fixture.createTodo(categoryId = categoryId))
            commentRepository.save(Fixture.createComment())
        }

        test("삭제 성공"){
            // when
            categoryService.deleteCategory(categoryId)
            // then
            categoryRepository.findById(categoryId) shouldBe null
            todoRepository.findById(Fixture.todoId) shouldBe null
            commentRepository.findById(Fixture.commentId) shouldBe null
        }

        test("해당 id의 카테고리가 없을 경우 실패"){
            // when
            val categoryId = categoryService.createCategory(createRequest)
            categoryService.deleteCategory(categoryId)
            // then
            shouldThrow<RuntimeException> {
                categoryService.deleteCategory(categoryId)
            }
        }
    }
})