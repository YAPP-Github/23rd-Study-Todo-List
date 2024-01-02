package com.example.studytodolist.common.swagger

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/swagger")
internal class SwaggerController {
    @GetMapping
    fun api(): String {
        return "redirect:/swagger-ui/index.html"
    }
}