package moe.saikyo47.controller

import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.entity.User
import moe.saikyo47.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class LoginController {
    @Autowired
    lateinit var loginService: LoginService

    @PostMapping("/login")
    fun login(@RequestBody user: User): ResponseResult<Any> {
        return loginService.login(user)
    }

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseResult<Any> {
        return loginService.register(user)
    }
}