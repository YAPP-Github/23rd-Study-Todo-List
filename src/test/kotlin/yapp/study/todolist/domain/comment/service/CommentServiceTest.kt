package yapp.study.todolist.domain.comment.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.dto.CommentContentDto
import yapp.study.todolist.domain.comment.dto.CommentDto
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.task.repository.TaskRepository
import yapp.study.todolist.domain.testFixture.Fixture
import java.time.LocalDate
import java.time.LocalTime

@SpringBootTest
class CommentServiceTest @Autowired constructor(
        private val categoryRepository: CategoryRepository,
        private val taskRepository: TaskRepository,
        private val commentService: CommentService,
        private val commentRepository: CommentRepository
):FunSpec({
    val createRequest = CommentDto(
            id = 1,
            taskId = 1,
            content = "11"
    )
    val createInvalidTaskIdRquest = CommentDto(
            id = 1,
            taskId = 2,
            content = "11"
    )
    val updateRequest = CommentContentDto(
            content = "1122"
    )
    val categoryId: Long = 1
    val taskId: Long = 1
    val commentId: Long = 1
    val invalidCommentId: Long = 2

    beforeEach {
        categoryRepository.save(Fixture.createCategory())
        taskRepository.save(Fixture.createTask())
    }
    afterEach {
        categoryRepository.deleteById(categoryId)
        taskRepository.deleteById(taskId)
        commentRepository.deleteById(commentId)
    }

    context("comment 생성 테스트"){
        test("생성 성공"){
            // when
            commentService.createComment(createRequest)
            // then
            val comment = commentRepository.findById(commentId)
            comment shouldNotBe null
            comment!!.id shouldBe commentId
            comment.taskId shouldBe taskId
            comment.content shouldBe "11"
        }

        test("id 중복시 생성 실패"){
            // when
            commentService.createComment(createRequest)
            // then
            shouldThrow<RuntimeException> {
                commentService.createComment(createRequest)
            }
        }

        test("존재하지않는 task의 comment 생성시 실패"){
            // then
            shouldThrow<RuntimeException> {
                commentService.createComment(createInvalidTaskIdRquest)
            }
        }
    }

    context("comment 수정 테스트"){
        beforeEach {
            commentService.createComment(createRequest)
        }

        test("수정 성공"){
            // when
            commentService.updateComment(commentId, updateRequest)
            // then
            val comment = commentRepository.findById(commentId)
            comment shouldNotBe null
            comment!!.id shouldBe commentId
            comment.taskId shouldBe taskId
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
        beforeEach {
            commentService.createComment(createRequest)
        }
        afterEach {
            commentService.createComment(createRequest)
        }

        test("삭제 성공"){
            // when
            commentService.deleteComment(commentId)
            // then
            commentRepository.findById(commentId) shouldBe null
        }

        test("존재하지않는 id일 경우 실패"){
            // when
            commentService.deleteComment(commentId)
            // then
            shouldThrow<RuntimeException> {
                commentService.deleteComment(commentId)
            }
        }
    }

})