package yapp.study.todolist.domain.comment.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yapp.study.todolist.domain.comment.dto.CommentContentDto
import yapp.study.todolist.domain.comment.dto.CommentDetailDto
import yapp.study.todolist.domain.comment.dto.CommentDto
import yapp.study.todolist.domain.comment.service.CommentService

@RestController
@RequestMapping("/comments")
class CommentController(
        private val commentService: CommentService
) {
    @PostMapping
    fun createComment(@RequestBody commentDetailDto: CommentDetailDto) {
        commentService.createComment(commentDetailDto)
    }

    @PatchMapping("/{id}")
    fun updateComment(@PathVariable("id") id: Long,
                      @RequestBody commentContentDto: CommentContentDto) {
        commentService.updateComment(id, commentContentDto)
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable("id") id: Long) {
        commentService.deleteComment(id)
    }
}