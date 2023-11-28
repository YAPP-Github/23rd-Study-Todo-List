package yapp.study.todolist.domain.viewer.repository

import org.springframework.data.repository.CrudRepository
import yapp.study.todolist.domain.viewer.entity.ViewerCount

interface ViewerCountRepository : CrudRepository<ViewerCount, Long>