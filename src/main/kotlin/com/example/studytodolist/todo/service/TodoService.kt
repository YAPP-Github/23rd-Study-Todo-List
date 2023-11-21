package com.example.studytodolist.todo.service

import com.example.studytodolist.common.exception.BusinessException
import com.example.studytodolist.common.exception.ErrorCode
import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo
import com.example.studytodolist.todo.dto.request.BulkSaveRequestDto
import com.example.studytodolist.todo.dto.request.TodoSaveRequestDto
import com.example.studytodolist.todo.dto.request.TodoUpdateRequestDto
import com.example.studytodolist.todo.dto.response.BulkSaveResponseDto
import com.example.studytodolist.todo.dto.response.TodoFindResponseDto
import com.example.studytodolist.todo.dto.response.TodoSaveResponseDto
import com.example.studytodolist.todo.dto.response.TodoUpdateResponseDto
import com.example.studytodolist.todo.repository.TodoBulkInsertRepository
import com.example.studytodolist.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.sql.SQLException

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val todoBulkInsertRepository: TodoBulkInsertRepository
) {
    @Transactional
    fun save(todoSaveRequestDto: TodoSaveRequestDto): TodoSaveResponseDto{
        val todo = TodoSaveRequestDto.toEntity(todoSaveRequestDto)
        return TodoSaveResponseDto(todoRepository.save(todo))
    }

    fun findAll(): List<TodoFindResponseDto>{
        return todoRepository.findAll().map{TodoFindResponseDto(it)}.toList()
    }

    fun findById(id: Long): TodoFindResponseDto {
        val todo = todoRepository.findByIdOrNull(id) ?: throw BusinessException(ErrorCode.TODO_NOT_FOUND)
        return TodoFindResponseDto(todo)
    }

    fun deleteById(id: Long) = todoRepository.deleteById(id)

    @Transactional
    fun update(todoUpdateRequestDto: TodoUpdateRequestDto): TodoUpdateResponseDto{
        val todo: Todo = todoRepository.findByIdOrNull(todoUpdateRequestDto.id) ?: throw BusinessException(ErrorCode.TODO_NOT_FOUND)
        todo.progress = todoUpdateRequestDto.progress
        return TodoUpdateResponseDto(todo)
    }

    fun bulkSave(bulkSaveRequestDto: BulkSaveRequestDto): BulkSaveResponseDto {
        var startIndex: Int = 1
        val bulkSize: Int = 1000
        val result: BulkSaveResponseDto = BulkSaveResponseDto(0)
        val count = bulkSaveRequestDto.count
        try {
            do{
                result.append(todoBulkInsertRepository.saveAll(
                    (startIndex..if (startIndex + bulkSize - 1> count) count else startIndex + bulkSize - 1)
                        .map { i -> Todo(title = "title-$i", content = "content-$i", progress = Progress.PROCESSING) }
                        .toMutableList()
                ))
                startIndex += bulkSize
            } while (startIndex <= count)
            return result
        } catch (e: SQLException) {
            throw BusinessException(ErrorCode.TODO_BULK_INSERT_FAILED)
        }
    }
}