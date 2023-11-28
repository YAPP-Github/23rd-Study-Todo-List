package yapp.study.todolist.domain.viewer.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import yapp.study.todolist.common.const.VIEWER_TTL
import java.time.LocalDateTime

@RedisHash("viewerId")
class ViewerId(
    @Id
    val id: Long,
    var viewAt: LocalDateTime = LocalDateTime.now(),
    @TimeToLive
    var ttl: Long = VIEWER_TTL
)