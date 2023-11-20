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
        val todoList = mutableListOf<Todo>()
        for (i: Int in 1..bulkSaveRequestDto.count){
            val todo: Todo = Todo(title = "title-$i", content = "content-$i", progress = Progress.PROCESSING)
            todoList.add(todo)
            System.out.println(todo.id)
        }
        return BulkSaveResponseDto(todoBulkInsertRepository.saveAll(todoList))
    }
}