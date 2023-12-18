package moe.saikyo47.service.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import moe.saikyo47.constant.Constant
import moe.saikyo47.domain.dto.LoginUser
import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.entity.User
import moe.saikyo47.enums.AppHttpCodeEnum
import moe.saikyo47.service.LoginService
import moe.saikyo47.service.TokenService
import moe.saikyo47.service.UserService
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
    lateinit var userService: UserService

    override fun login(user: User): ResponseResult<Any> {
        val token = UsernamePasswordAuthenticationToken(user.userName, user.password)
        val authentication = authenticationManager.authenticate(token)

        if (Objects.isNull(authentication)) {
            throw RuntimeException("用户名或密码错误")
        }

        val loginUser: LoginUser = authentication.principal as LoginUser
        val jwt = TokenService.createToken(loginUser.username, loginUser.password)
        return ResponseResult(AppHttpCodeEnum.SUCCESS, jwt)
    }

    override fun register(user: User): ResponseResult<Any> {
        try {
            // check if duplicate username
            val userNameWrapper = userService.getOne(KtQueryWrapper(User()).eq(User::userName, user.userName))
            if (Objects.isNull(userNameWrapper)) {
                // check if duplicate email
                val emailWrapper = userService.getOne(KtQueryWrapper(User()).eq(User::email, user.userName))
                if (Objects.isNull(emailWrapper)) {
                    val pwd = passwordEncoder.encode(user.password)
                    val original = user.password
                    user.password = pwd
                    user.permissionGroup = Constant.Permission.USER
                    userService.save(user)
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
        if (token.isNullOrEmpty()) {
            return User(permissionGroup = Constant.Permission.GUEST)
        } else {
            val userName = TokenService.parseToken(token).subject
            return userService.getOne(KtQueryWrapper(User()).eq(User::userName, userName))
                .default(User(permissionGroup = Constant.Permission.GUEST))
        }

    }
}