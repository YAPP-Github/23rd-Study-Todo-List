package yapp.study.todolist.common.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.headers.Header
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import jakarta.servlet.ServletContext
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun openAPI(servletContext: ServletContext): OpenAPI {
        val server = Server().url(servletContext.contextPath)

        return OpenAPI()
            .servers(listOf(server))
//            .components(components())
            .info(swaggerInfo())
    }

    private fun swaggerInfo(): Info {
        val license = License()
        license.url = "https://github.com/YAPP-Github/23rd-Study-Todo-List"
        license.name = "todo"
        return Info()
            .title("\"todo API문서\"")
            .license(license)
    }

//    private fun components(): Components {
//        val header = Header()
//            .content("application/json")
//        return Components()
//            .addHeaders("Content-Type", )
//    }
}