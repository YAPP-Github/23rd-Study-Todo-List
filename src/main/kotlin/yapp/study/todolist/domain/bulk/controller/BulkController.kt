package yapp.study.todolist.domain.bulk.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yapp.study.todolist.domain.bulk.service.BulkService
import yapp.study.todolist.domain.category.dto.CategoryNameDto

@RestController
@RequestMapping(value = ["/v1/bulks"],
//        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
class BulkController(
    val bulkService: BulkService
) {
    @PostMapping
    fun bulkInsert() {
        bulkService.bulkInsert();
    }
}