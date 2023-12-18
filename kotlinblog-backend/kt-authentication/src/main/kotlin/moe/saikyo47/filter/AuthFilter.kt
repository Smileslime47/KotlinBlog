package moe.saikyo47.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import moe.saikyo47.service.impl.LoginServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        if (token != null) {
            System.out.println("token: $token")
            if (LoginServiceImpl.userMap.containsKey(token)) {
                System.out.println("userMap: ${LoginServiceImpl.userMap[token]}")
                val user = LoginServiceImpl.userMap[token]
                val authToken = UsernamePasswordAuthenticationToken(user?.userName, user?.password)
                authToken.isAuthenticated = true
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }
}