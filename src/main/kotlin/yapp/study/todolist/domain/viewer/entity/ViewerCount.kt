package yapp.study.todolist.domain.viewer.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import yapp.study.todolist.common.const.VIEWER_TTL
import yapp.study.todolist.common.error.errors.BadRequestException

@RedisHash("viewerCount")
class ViewerCount(
    @Id
    val id: Long = 1,
    @TimeToLive
    var ttl: Long = VIEWER_TTL,
    var count: Long = 1,
) {
    fun increaseCount() {
        this.count++
        this.ttl = VIEWER_TTL
    }

    fun decreaseCount() {
        if (count == 0L) {
            throw BadRequestException("이미 viewer 수가 0 입니다.")
        }
        this.count--
        this.ttl = VIEWER_TTL
    }

    fun extendTtl() {
        this.ttl = VIEWER_TTL
    }
}