package yapp.study.todolist.domain.comment.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import yapp.study.todolist.domain.category.repository.CategoryRepository
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
    val createInvalidIdRequest = CommentDto(
            id = 2,
            taskId = 1,
            content = "11"
    )
    val createInvalidTaskIdRquest = CommentDto(
            id = 1,
            taskId = 2,
            content = "11"
    )
    val categoryId: Long = 1
    val taskId: Long = 1
    val commentId: Long = 1
    val invalidCommentId: Long = 1

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

//    context("task 수정 테스트"){
//        beforeEach {
//            taskService.createTask(createRequest)
//        }
//
//        test("수정 성공"){
//            // when
//            taskService.updateTask(taskId, updateRequest)
//            // then
//            val task = taskRepository.findById(taskId)
//            task shouldNotBe null
//            task!!.id shouldBe 1
//            task.categoryId shouldBe 1
//            task.title shouldBe "12"
//            task.memo shouldBe "1122"
//            task.date shouldBe LocalDate.MIN
//            task.fromTime shouldBe LocalTime.MIN
//            task.toTime shouldBe LocalTime.MAX
//            task.isDone shouldBe false
//        }
//
//        test("존재하지않는 task id일 경우 실패"){
//            // then
//            shouldThrow<RuntimeException> {
//                taskService.updateTask(invalidTaskId, updateRequest)
//            }
//        }
//
//        test("존재하지않는 카테고리 id일 경우 실패"){
//            // then
//            shouldThrow<RuntimeException> {
//                taskService.updateTask(taskId, updateInvalidCategoryIdRequest)
//            }
//        }
//    }
//
//    context("task 삭제 테스트"){
//        beforeEach {
//            taskService.createTask(createRequest)
//        }
//        afterEach {
//            taskService.createTask(createRequest)
//        }
//
//        test("삭제 성공"){
//            // when
//            taskService.deleteTask(taskId)
//            // then
//            taskRepository.findById(taskId) shouldBe null
//        }
//
//        test("존재하지않는 id일 경우 실패"){
//            // when
//            taskService.deleteTask(taskId)
//            // then
//            shouldThrow<RuntimeException> {
//                taskService.deleteTask(taskId)
//            }
//        }
//    }

})