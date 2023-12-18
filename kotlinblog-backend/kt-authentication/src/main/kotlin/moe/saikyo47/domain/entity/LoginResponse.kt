package moe.saikyo47.domain.entity

import org.apache.logging.log4j.util.Base64Util

class LoginResponse(accessToken: String, refreshToken: String) {
    var accessToken: String = accessToken
    var refreshToken: String = refreshToken

    override fun toString(): String {
        return "$accessToken,$refreshToken"
    }
}