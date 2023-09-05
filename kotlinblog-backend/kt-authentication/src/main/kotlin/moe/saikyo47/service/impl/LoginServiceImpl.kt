package moe.saikyo47.service.impl

import moe.saikyo47.domain.dto.LoginUser
import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.entity.User
import moe.saikyo47.enums.AppHttpCodeEnum
import moe.saikyo47.service.LoginService
import moe.saikyo47.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class LoginServiceImpl : LoginService {
    @Autowired
    @Lazy
    lateinit var authenticationManager: AuthenticationManager

    override fun login(user: User): ResponseResult<Any> {
        val token = UsernamePasswordAuthenticationToken(user.userName, user.password)
        val authentication = authenticationManager.authenticate(token)

        if (Objects.isNull(authentication)) {
            throw RuntimeException("用户名或密码错误")
        }

        val loginUser: LoginUser = authentication.principal as LoginUser
        val userId = loginUser.user.id.toString()
        val jwt = JwtUtil.createJWT(userId)
        return ResponseResult(AppHttpCodeEnum.SUCCESS, jwt)
    }
}