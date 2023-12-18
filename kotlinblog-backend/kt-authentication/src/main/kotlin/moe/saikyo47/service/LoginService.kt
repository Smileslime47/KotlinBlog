package moe.saikyo47.service

import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.entity.User

/**
 * Login功能接口
 *
 * @author Smile_slime_47
 * @since 2023-09-03
 */
interface LoginService {
    fun login(user: User): ResponseResult<Any>
    fun register(user: User): ResponseResult<Any>
    fun getUserByToken(token: String?): User
}