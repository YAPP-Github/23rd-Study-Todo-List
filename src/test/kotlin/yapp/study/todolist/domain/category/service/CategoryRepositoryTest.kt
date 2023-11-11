package yapp.study.todolist.domain.category.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import yapp.study.todolist.domain.category.dto.CategoryDto
import yapp.study.todolist.domain.category.dto.CategoryNameDto
import yapp.study.todolist.domain.category.repository.CategoryRepository

@SpringBootTest
class CategoryRepositoryTest @Autowired constructor(
        private val categoryService: CategoryService,
        private val categoryRepository: CategoryRepository
) : FunSpec({
    val createRequest = CategoryDto(
            id = 1,
            name = "1111"
    )
    val createFailureRequest = CategoryDto(
            id = 1,
            name = "2222"
    )
    val categoryId: Long = 1
    val invalidCategoryId: Long = 2


    context("카테고리 생성 테스트"){
        afterEach {
            categoryRepository.deleteById(categoryId)
        }

        test("생성 성공"){
            // when
            categoryService.createCategory(createRequest)
            // then
            val category = categoryRepository.findById(categoryId)
            category shouldNotBe null
            category!!.id shouldBe categoryId
            category.name shouldBe "1111"
        }

        test("아이디 중복시 실패"){
            // when
            categoryService.createCategory(createRequest)
            // then
            shouldThrow<RuntimeException> {
                categoryService.createCategory(createFailureRequest)
            }
        }
    }

    context("카테고리 수정 테스트"){
        beforeEach {
            categoryService.createCategory(createRequest)
        }
        afterEach {
            categoryRepository.deleteById(categoryId)
        }

        val updateRequest = CategoryNameDto(
                name = "11112222"
        )

        test("수정 성공"){
            // when
            categoryService.updateCategory(categoryId, updateRequest)
            // then
            val category = categoryRepository.findById(categoryId)
            category shouldNotBe null
            category!!.id shouldBe 1
            category.name shouldBe "11112222"
        }

        test("해당 id의 카테고리가 없을 경우 실패"){
            // then
            shouldThrow<RuntimeException> {
                categoryService.updateCategory(invalidCategoryId, updateRequest)
            }
        }
    }

    context("카테고리 삭제 테스트"){
        beforeEach {
            categoryService.createCategory(createRequest)
        }

        test("삭제 성공"){
            // when
            categoryService.deleteCategory(categoryId)
            // then
            categoryRepository.findById(categoryId) shouldBe null
        }

        test("해당 id의 카테고리가 없을 경우 실패"){
            // when
            categoryService.deleteCategory(categoryId)
            // then
            shouldThrow<RuntimeException> {
                categoryService.deleteCategory(categoryId)
            }
        }
    }
})