package moe.saikyo47.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import moe.saikyo47.domain.entity.LoginResponse
import org.springframework.stereotype.Service
import javax.crypto.SecretKey

@Service
class TokenService {
    companion object {

        private val secret: SecretKey =
            Keys.hmacShaKeyFor("rhjomilejdwkhmgvhzhxqprkzhatxqcubxscqqrnedcfuczfcyddqjelakithgnyxplmtxjtawdcxdnktbcaxedyiphzemrwgleceeqrzigmsdlyqsjdscifagcbvwfpisfrgcatnljuinjrpytkpbrkegvdipcxnbwdqafnguswobhbzdvvijciznxckjtnxsnrgfitzttkfqfgbjnoibovjqsmqadhvjrzvwepocwmlckaowmjyiffhnhpmava".toByteArray())

        fun createToken(username: String, password: String): LoginResponse {
            val accessToken = createAccessToken(username)
            val refreshToken = createRefreshToken(username, password)
            return LoginResponse(accessToken, refreshToken)
        }

        private fun createAccessToken(username: String): String =
            Jwts.builder().subject(username).signWith(secret).compact()

        private fun createRefreshToken(username: String, password: String): String =
            Jwts.builder().subject(username + password).signWith(secret).compact()

        fun parseToken(token: String): Claims {
            return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).payload
        }
    }

}