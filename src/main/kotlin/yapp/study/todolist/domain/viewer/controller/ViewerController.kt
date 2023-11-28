package yapp.study.todolist.domain.viewer.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import yapp.study.todolist.domain.viewer.service.ViewerService

@RestController
@RequestMapping(value = ["/v1/viewers"],
    produces = [MediaType.APPLICATION_JSON_VALUE])
class ViewerController(
    private val viewerService: ViewerService
) {
    @PatchMapping("{id}")
    fun updateCount(@PathVariable("id") id: Long,
                    @RequestParam("increase") increase: Boolean) {
        viewerService.updateCount(increase, id)
    }

    @PatchMapping("{id}/extends")
    fun extendTtl(@PathVariable("id") id: Long) {
        viewerService.extendTtl(id)
    }
}