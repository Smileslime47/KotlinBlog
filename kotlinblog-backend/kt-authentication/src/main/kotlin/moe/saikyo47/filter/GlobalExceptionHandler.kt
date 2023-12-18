package moe.saikyo47.filter

import io.jsonwebtoken.JwtException
import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.enums.AppHttpCodeEnum
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(JwtException::class)
    fun exceptionHandler(e: JwtException): ResponseResult<Any> {
        return ResponseResult(AppHttpCodeEnum.LOGIN_ERROR, "无效的登陆状态")
    }

}