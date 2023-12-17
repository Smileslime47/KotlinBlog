package moe.saikyo47.controller

import jakarta.servlet.http.HttpServletRequest
import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.entity.User
import moe.saikyo47.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class LoginController {
    @Autowired
    lateinit var loginService: LoginService
    @PostMapping("/login")
    fun login(username: String, password: String): ResponseResult<Any> {
        val user = User()
        user.userName= username
        user.password = password
        return loginService.login(user)
    }
}