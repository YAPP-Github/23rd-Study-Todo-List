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
        fun toEntity(categoryDto: CategoryDto): Category {
            return Category(
                    id = categoryDto.id,
                    name = categoryDto.name
            )
        }
    }

    fun updateName(name: String) {
        this.name = name
    }
}