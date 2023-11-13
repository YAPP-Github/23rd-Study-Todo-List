package yapp.study.todolist.domain.comment.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.dto.CommentContentDto
import yapp.study.todolist.domain.comment.dto.CommentDetailDto
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.todo.repository.TodoRepository
import yapp.study.todolist.domain.testFixture.Fixture

@SpringBootTest
class CommentServiceTest @Autowired constructor(
        private val categoryRepository: CategoryRepository,
        private val todoRepository: TodoRepository,
        private val commentService: CommentService,
        private val commentRepository: CommentRepository
):FunSpec({
    val todoId: Long = 1
    val invalidCommentId: Long = 2
    val createRequest = CommentDetailDto(
            todoId = 1,
            content = "11"
    )
    val createInvalidTodoIdRquest = CommentDetailDto(
            todoId = 2,
            content = "11"
    )
    val updateRequest = CommentContentDto(
            content = "1122"
    )

    beforeEach {
        categoryRepository.save(Fixture.createCategory())
        todoRepository.save(Fixture.createTodo())
    }
    afterEach {
        categoryRepository.deleteAll()
        todoRepository.deleteAll()
        commentRepository.deleteAll()
    }

    context("comment 생성 테스트"){
        test("생성 성공"){
            // when
            val commentId = commentService.createComment(createRequest)
            // then
            val comment = commentRepository.findById(commentId)
            comment shouldNotBe null
            comment!!.id shouldBe commentId
            comment.todoId shouldBe todoId
            comment.content shouldBe "11"
        }

        test("존재하지않는 todo의 comment 생성시 실패"){
            // then
            shouldThrow<RuntimeException> {
                commentService.createComment(createInvalidTodoIdRquest)
            }
        }
    }

    context("comment 수정 테스트"){

        test("수정 성공"){
            // when
            val commentId = commentService.createComment(createRequest)
            commentService.updateComment(commentId, updateRequest)
            // then
            val comment = commentRepository.findById(commentId)
            comment shouldNotBe null
            comment!!.id shouldBe commentId
            comment.todoId shouldBe todoId
            comment.content shouldBe "1122"
        }

        test("존재하지않는 comment id일 경우 실패"){
            // then
            shouldThrow<RuntimeException> {
                commentService.updateComment(invalidCommentId, updateRequest)
            }
        }
    }

    context("comment 삭제 테스트"){
        test("삭제 성공"){
            // when
            val commentId = commentService.createComment(createRequest)
            commentService.deleteComment(commentId)
            // then
            commentRepository.findById(commentId) shouldBe null
        }

        test("존재하지않는 id일 경우 실패"){
            // when
            val commentId = commentService.createComment(createRequest)
            commentService.deleteComment(commentId)
            // then
            shouldThrow<RuntimeException> {
                commentService.deleteComment(commentId)
            }
        }
    }

})