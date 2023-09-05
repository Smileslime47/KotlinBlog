package moe.saikyo47.service

import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.entity.User

interface LoginService {
    fun login(user: User):ResponseResult<Any>
}