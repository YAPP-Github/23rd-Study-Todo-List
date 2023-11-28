package yapp.study.todolist.domain.category.entity

import jakarta.persistence.*
import yapp.study.todolist.domain.base.BaseEntity

@Entity
@Table(name = "category")
class Category(
        var name: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "category_id")
        val id: Long = -1,
) : BaseEntity() {
    companion object {
        fun toEntity(name: String): Category {
            return Category(
                    name = name
            )
        }
    }

    fun updateName(name: String) {
        this.name = name
    }
}