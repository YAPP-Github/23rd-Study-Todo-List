package yapp.study.todolist.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import yapp.study.todolist.domain.category.repository.CategoryRepository

@Configuration
@EnableJpaAuditing
class JpaConfig