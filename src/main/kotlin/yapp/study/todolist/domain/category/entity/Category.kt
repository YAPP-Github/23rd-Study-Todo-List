package yapp.study.todolist.domain.category.entity

import yapp.study.todolist.common.annotation.TempNoArg
import yapp.study.todolist.domain.base.BaseEntity
import yapp.study.todolist.domain.category.dto.CategoryDto

@TempNoArg
class Category(
        val id: Long,
        var name: String
) : BaseEntity() {
    companion object {
        fun toEntity(id: Long, name: String): Category {
            return Category(
                    id = id,
                    name = name
            )
        }
    }

    fun updateName(name: String) {
        this.name = name
    }
}