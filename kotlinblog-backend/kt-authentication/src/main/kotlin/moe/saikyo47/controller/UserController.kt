package moe.saikyo47.controller

import jakarta.servlet.http.HttpServletRequest
import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.vo.UserVo
import moe.saikyo47.enums.AppHttpCodeEnum
import moe.saikyo47.service.LoginService
import moe.saikyo47.utils.BeanCopyUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/api/user")
class UserController {
    @Autowired
    lateinit var loginService: LoginService

    @GetMapping("/currentUser")
    fun getUserByToken(req : HttpServletRequest): ResponseResult<UserVo?> {
        val token = req.getHeader("Authorization")
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            null,
            BeanCopyUtils.beanCopy(loginService.getUserByToken(token), UserVo::class.java)
        )
    }
}