package com.example.studytodolist.todo.dto.response

data class BulkSaveResponseDto(
    var count: Int
){
    fun append(num: Int){
        count += num
    }
}
