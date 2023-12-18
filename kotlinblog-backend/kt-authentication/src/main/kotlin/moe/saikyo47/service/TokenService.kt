package moe.saikyo47.service

import io.jsonwebtoken.Jwts
import moe.saikyo47.domain.entity.LoginResponse
import org.springframework.stereotype.Service

@Service
class TokenService {

    public fun createToken(username: String, password: String): LoginResponse {
        val accessToken = createAccessToken(username)
        val refreshToken = createRefreshToken(username, password)
        return LoginResponse(accessToken, refreshToken)
    }

    private fun createAccessToken(username: String): String =
        Jwts.builder().subject(username).signWith(Jwts.SIG.HS256.key().build()).compact()

    private fun createRefreshToken(username: String, password: String): String =
        Jwts.builder().subject(username + password).signWith(Jwts.SIG.HS256.key().build()).compact()

}