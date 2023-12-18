package moe.saikyo47.service.impl

import moe.saikyo47.constant.Constant
import moe.saikyo47.domain.dto.LoginUser
import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.entity.User
import moe.saikyo47.enums.AppHttpCodeEnum
import moe.saikyo47.mapper.UserMapper
import moe.saikyo47.service.LoginService
import moe.saikyo47.service.TokenService
import moe.saikyo47.utils.Maybe.Companion.default
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

/**
 * Login功能Service类
 *
 * @author Smile_slime_47
 * @since 2023-09-03
 */
@Service
class LoginServiceImpl : LoginService {
    @Autowired
    @Lazy
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    @Lazy
    lateinit var tokenService: TokenService

    @Autowired
    @Lazy
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    @Lazy
    lateinit var userMapper: UserMapper

    // a simple in-memory hashmap to store the user information
    companion object {
        val userMap: MutableMap<String, User> = mutableMapOf()
    }

    override fun login(user: User): ResponseResult<Any> {
        val token = UsernamePasswordAuthenticationToken(user.userName, user.password)
        val authentication = authenticationManager.authenticate(token)

        if (Objects.isNull(authentication)) {
            throw RuntimeException("用户名或密码错误")
        }

        val loginUser: LoginUser = authentication.principal as LoginUser
        val jwt = tokenService.createToken(loginUser.username, loginUser.password)
        userMap[jwt.accessToken] = loginUser.user
        return ResponseResult(AppHttpCodeEnum.SUCCESS, jwt)
    }

    override fun register(user: User): ResponseResult<Any> {
        try {
            // check if duplicate username
            val userWrapper = userMapper.selectById(user.userName)
            if (Objects.isNull(userWrapper)) {
                // check if duplicate email
                val emailWrapper = userMapper.selectById(user.email)
                if (Objects.isNull(emailWrapper)) {
                    val pwd = passwordEncoder.encode(user.password)
                    val original = user.password
                    user.password = pwd
                    user.permissionGroup = Constant.Permission.USER
                    userMapper.insert(user)
                    user.password = original
                    return ResponseResult(AppHttpCodeEnum.SUCCESS, "注册成功", login(user))
                } else {
                    return ResponseResult(AppHttpCodeEnum.EMAIL_EXIST, "邮箱已被注册过")
                }
            } else {
                return ResponseResult(AppHttpCodeEnum.USERNAME_EXIST, "用户名已被注册过")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseResult(AppHttpCodeEnum.SYSTEM_ERROR, "数据库错误")
        }
    }

    override fun getUserByToken(token: String?): User {
        return userMap.getOrElse(
            token.default("")
        ) {
            val user = User()
            user.permissionGroup = Constant.Permission.GUEST
            return user
        }

    }
}