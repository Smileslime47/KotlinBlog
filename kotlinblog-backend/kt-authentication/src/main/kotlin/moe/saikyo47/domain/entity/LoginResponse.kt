package moe.saikyo47.domain.entity

class LoginResponse(accessToken: String, refreshToken: String) {
    var accessToken: String = accessToken
    var refreshToken: String = refreshToken

    override fun toString(): String {
        return "$accessToken,$refreshToken"
    }
}