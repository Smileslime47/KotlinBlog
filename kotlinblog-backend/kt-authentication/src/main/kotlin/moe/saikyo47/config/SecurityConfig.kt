package moe.saikyo47.config

import moe.saikyo47.constant.Constant
import moe.saikyo47.enums.AppHttpCodeEnum
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
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
    @Autowired
    lateinit var authenticationConfiguration: AuthenticationConfiguration

    /**
     * Spring Security策略配置
     */
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            //接口鉴权策略
            .authorizeHttpRequests { authorize ->
                authorize
                    // allowed for root-categories
                    .requestMatchers(Constant.ApiPath.CATEGORY_API + Constant.ApiPath.CATEGORY_ROOT_CATEGORIES)
                    .permitAll()
                    .requestMatchers(Constant.ApiPath.ARTICLE_API + "/info/**")
                    .permitAll()
                    .requestMatchers(POST, "/api/login")
                    .anonymous()
                    .requestMatchers(GET, Constant.ApiPath.ARTICLE_API + Constant.ApiPath.ARTICLE_DETAIL_BY_ID)
                    .authenticated()
                    .anyRequest()
                    // leave a dummy authentication lambda here
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
            .csrf { csrf ->
                csrf.disable()
            }
            .exceptionHandling { exceptionHandling ->
                exceptionHandling
                    .authenticationEntryPoint { _, response, _ ->
                        response.sendError(AppHttpCodeEnum.NEED_LOGIN.code, "请登录")
                    }
                    .accessDeniedHandler { _, response, _ ->
                        response.sendError(AppHttpCodeEnum.NO_OPERATOR_AUTH.code, "无权限操作")
                    }
            }
            .build()

    /**
     * 注册全局的AuthenticationManager Bean
     */
    @Bean
    fun getAuthenticationManagerBean(http: HttpSecurity): AuthenticationManager =
        authenticationConfiguration.authenticationManager

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(
        userDetailService: UserDetailsService,
        passwordEncoder: PasswordEncoder
    ): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailService)
        provider.setPasswordEncoder(passwordEncoder)
        return provider
    }
}