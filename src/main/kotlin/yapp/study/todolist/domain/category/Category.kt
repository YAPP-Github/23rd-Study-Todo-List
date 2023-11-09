package yapp.study.todolist.domain.category

import yapp.study.todolist.domain.base.BaseEntity

class Category(
        val id: Long,
        val name: String
) : BaseEntity()