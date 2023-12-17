package moe.saikyo47.domain.dto

import moe.saikyo47.domain.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * UserDetail类，用于Spring Security的权限鉴定
 *
 * @author Smile_slime_47
 * @since 2023-09-03
 */
class LoginUser(val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null;
    }

    override fun getPassword(): String = user.password!!
    override fun getUsername(): String = user.userName!!
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}