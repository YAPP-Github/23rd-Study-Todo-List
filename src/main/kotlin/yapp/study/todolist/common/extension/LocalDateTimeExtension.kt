package yapp.study.todolist.common.extension

import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.toLong(): Long {
    return this.atZone(ZoneId.systemDefault()).toEpochSecond()
}
