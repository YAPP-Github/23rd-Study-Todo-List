package yapp.study.todolist.domain.base

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(
        @CreatedDate
        @Column(updatable = false)
        var createdAt: LocalDateTime = LocalDateTime.now(),

        @LastModifiedDate
        @Column(updatable = true)
        val updatedAt: LocalDateTime = LocalDateTime.now(),
)