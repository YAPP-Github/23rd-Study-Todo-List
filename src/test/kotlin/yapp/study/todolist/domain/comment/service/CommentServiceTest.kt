package yapp.study.todolist.domain.comment.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import yapp.study.todolist.domain.comment.dto.CommentContentDto
import yapp.study.todolist.domain.comment.dto.CommentDetailDto
import yapp.study.todolist.domain.comment.entity.Comment
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.testFixture.Fixture
import yapp.study.todolist.domain.todo.repository.TodoRepository

@ExtendWith(MockKExtension::class)
class CommentServiceTest: FunSpec({
    val todoRepository = mockk<TodoRepository>()
    val commentRepository = mockk<CommentRepository>()
    var commentService = CommentService(todoRepository, commentRepository)
    val todoId: Long = 1
    val invalidTodoId: Long = 2
    val commentId: Long = 1
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
    val comment = Fixture.createComment()

    every { commentRepository.save(any()) } returns comment

    context("comment 생성 테스트"){
        every { todoRepository.existsById(todoId) } returns true
        every { todoRepository.existsById(invalidTodoId) } returns false
        test("생성 성공"){
            // when
            val id = commentService.createComment(createRequest)
            // then
            id shouldBe commentId
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
            // given
            every { commentRepository.findByIdOrNull(commentId) } returns Comment.toEntity(createRequest)
            // when
            commentService.updateComment(commentId, updateRequest)
        }

        test("존재하지않는 comment id일 경우 실패"){
            // given
            every { commentRepository.findByIdOrNull(commentId) } returns null
            // then
            shouldThrow<RuntimeException> {
                commentService.updateComment(invalidCommentId, updateRequest)
            }
        }
    }

    context("comment 삭제 테스트"){
        test("삭제 성공"){
            // given
            every { commentRepository.existsById(commentId) } returns true
            every { commentRepository.deleteById(any()) } returns Unit
            // when
            commentService.deleteComment(commentId)
        }

        test("존재하지않는 id일 경우 실패"){
            // given
            every { commentRepository.existsById(invalidCommentId) } returns false
            // then
            shouldThrow<RuntimeException> {
                commentService.deleteComment(invalidCommentId)
            }
        }
    }

})