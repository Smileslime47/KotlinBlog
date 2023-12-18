package moe.saikyo47.filter

import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import moe.saikyo47.service.TokenService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter

@Service
class AuthFilter(val userDetailsService: UserDetailsService) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        if (token != null) {
            try {
                val username = TokenService.parseToken(token).subject
                val details = userDetailsService.loadUserByUsername(username)
                val authToken = UsernamePasswordAuthenticationToken(details, null, details.authorities)
                SecurityContextHolder.getContext().authentication = authToken
            } catch (_: JwtException) {

            } catch (_: IllegalArgumentException) {

            }

        }
        filterChain.doFilter(request, response)
    }
}