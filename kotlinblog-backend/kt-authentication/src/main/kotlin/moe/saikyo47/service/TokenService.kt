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

@Service
class TokenService {
//    private var secretKey: Key? = null
    lateinit var secretKey: Key

    @PostConstruct
    private fun init() {
        val key = "最強stronger"
        secretKey = Keys.hmacShaKeyFor(key.toByteArray())
    }

    public fun createToken(username: String, password: String): LoginResponse {
        val accessToken = createAccessToken(username)
        return LoginResponse(accessToken, "refreshToken")
    }

    private fun createAccessToken(username: String): String =
        Jwts.builder().subject(username).signWith(secretKey).compact()
}