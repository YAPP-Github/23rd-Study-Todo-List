package yapp.study.todolist.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        val allowedOriginPatterns = ArrayList<String>()
        allowedOriginPatterns.add("http://localhost:8080")

        val patterns = allowedOriginPatterns.toTypedArray()
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOriginPatterns(*patterns)
                .exposedHeaders("Set-Cookie")
                .allowCredentials(true)
    }

}