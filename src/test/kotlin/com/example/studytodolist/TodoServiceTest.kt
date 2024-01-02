package com.example.studytodolist

import com.example.studytodolist.common.exception.BusinessException
import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo
import com.example.studytodolist.todo.dto.request.BulkSaveRequestDto
import com.example.studytodolist.todo.dto.request.TodoSaveRequestDto
import com.example.studytodolist.todo.dto.request.TodoUpdateRequestDto
import com.example.studytodolist.todo.repository.TodoBulkInsertRepository
import com.example.studytodolist.todo.repository.TodoRepository
import com.example.studytodolist.todo.service.TodoService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.springframework.data.repository.findByIdOrNull

internal class TodoServiceTest: BehaviorSpec({
    val todoRepository = mockk<TodoRepository>()
    val todoBulkInsertRepository = mockk<TodoBulkInsertRepository>()
    val todoService = TodoService(todoRepository, todoBulkInsertRepository)
    val todoSaveRequest = TodoSaveRequestDto("test1", "test1", Progress.PROCESSING)
    val bulkSaveCount = 1000
    val todoBulkSaveRequest = BulkSaveRequestDto(bulkSaveCount);
    val todoUpdateRequest = TodoUpdateRequestDto(1, Progress.COMPLETED)
    val todoDeleteRequest = 1L
    val todo = Todo(1, "test1", "test1", Progress.PROCESSING)

    given("Todo 생성 테스트"){
        `when`("저장 성공"){
            every { todoRepository.save(any()) } returns todo
            val savedTodo = todoService.save(todoSaveRequest)
            then("저장한 title과 저장된 title이 같다."){
                savedTodo.title shouldBe todoSaveRequest.title
            }
        }
        `when`("Bulk 저장 성공"){
            every { todoBulkInsertRepository.saveAll(any()) } returns bulkSaveCount
            val bulkSaveResponse = todoService.bulkSave(todoBulkSaveRequest)
            then("저장시 요청한 count와 반환받은 count가 같다."){
                bulkSaveResponse.count shouldBe bulkSaveCount
            }
        }
    }
    given("Todo 조회 테스트"){
        `when`("id를 갖는 todo가 없을 시 실패"){
            every { todoRepository.findByIdOrNull(any()) } returns null
            then("BusinessException이 발생한다"){
                shouldThrow<BusinessException> {
                    todoService.findById(0);
                }
            }
        }
    }
    given("Todo 수정 테스트"){
        `when`("수정 성공"){
            every { todoRepository.findByIdOrNull(todoUpdateRequest.id) } returns todo
            val updatedTodo = todoService.update(todoUpdateRequest)
            then("update 요청한 progress와 반환받은 progress가 같다."){
                updatedTodo.progress shouldBe todoUpdateRequest.progress
            }
        }
        `when`("id를 갖는 todo가 없을 시 실패"){
            every { todoRepository.findByIdOrNull(any()) } returns null
            then("BusinessException이 발생한다"){
                shouldThrow<BusinessException> {
                    todoService.update(todoUpdateRequest)
                }
            }
        }
    }
    given("Todo 삭제 테스트"){
        `when`("삭제 성공"){
            every { todoRepository.findByIdOrNull(todoUpdateRequest.id) } returns todo
            every { todoRepository.deleteById(any()) } returns Unit
            then("에러가 발생하지 않는다."){
                todoService.deleteById(todoDeleteRequest)
            }
        }
    }
})