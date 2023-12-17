package moe.saikyo47.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ClaimsBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import moe.saikyo47.domain.entity.LoginResponse
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.crypto.SecretKey

@Service
class TokenService {

    public fun createToken(username: String, password: String): LoginResponse {
        val accessToken = createAccessToken(username)
        return LoginResponse(accessToken, "refreshToken")
    }

    private fun createAccessToken(username: String): String =
        Jwts.builder().subject(username).signWith(Jwts.SIG.HS256.key().build()).compact()
}