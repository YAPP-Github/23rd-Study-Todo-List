package yapp.study.todolist.domain.task.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import yapp.study.todolist.domain.task.dto.TaskCommentDto
import yapp.study.todolist.domain.task.dto.TaskDetailDto
import yapp.study.todolist.domain.task.dto.TaskDto
import yapp.study.todolist.domain.task.dto.TasksDto
import yapp.study.todolist.domain.task.service.TaskService

@RestController
@RequestMapping("/tasks")
class TaskController(
        private val taskService: TaskService
) {
    @PostMapping
    fun createTask(@RequestBody taskDto: TaskDto) {
        taskService.createTask(taskDto)
    }

    @GetMapping
    fun getTasks(): TasksDto {
        return taskService.getTasks()
    }

    @PatchMapping("/{id}")
    fun updateTask(@PathVariable("id") id: Long,
                   @RequestBody taskDetailDto: TaskDetailDto) {
        taskService.updateTask(id, taskDetailDto)
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable("id") id: Long) {
        taskService.deleteTask(id);
    }

    @PatchMapping("/{id}/done")
    fun updateTaskDone(@PathVariable("id") id: Long,
                       @RequestParam("done") done: Boolean) {
        taskService.updateDoneTask(id, done)
    }

    @GetMapping("/{id}/comments")
    fun getTaskWithComments(@PathVariable("id") id: Long): TaskCommentDto {
        return taskService.getTaskWithComments(id)
    }
}