package moe.saikyo47.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


/**
 * Spring Security配置类
 *
 * @author Smile_slime_47
 * @since 2023-09-03
 */
@Configuration
@EnableWebSecurity
class SecurityConfig {
    /**
     * Spring Security策略配置
     */
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            //接口鉴权策略
            .authorizeHttpRequests { authorize ->
                authorize
                    .anyRequest()
                    .permitAll()
            }
            //跨域访问策略
            .cors { cors ->
                val corsConfiguration = CorsConfiguration()
                //允许跨域携带Cookie
                corsConfiguration.allowCredentials = true
                //跨域访问允许的范围
                corsConfiguration.allowedHeaders = listOf("*")
                corsConfiguration.allowedMethods = listOf("*")
                corsConfiguration.allowedOriginPatterns = listOf("*")
                corsConfiguration.maxAge = 3600L
                val source = UrlBasedCorsConfigurationSource()
                //应用到目录下所有接口
                source.registerCorsConfiguration("/**", corsConfiguration)
                //应用跨域策略
                cors.configurationSource(source)
            }
            .build()

    /**
     * 注册AuthenticationManager Bean
     */
    @Bean
    fun getAuthenticationManagerBean(http: HttpSecurity): AuthenticationManager =
        http.getSharedObject(AuthenticationManagerBuilder::class.java).build()
}