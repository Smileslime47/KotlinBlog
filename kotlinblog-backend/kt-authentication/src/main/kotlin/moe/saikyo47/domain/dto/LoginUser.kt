package moe.saikyo47.domain.dto

import moe.saikyo47.domain.entity.User
import moe.saikyo47.mapper.GroupMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * UserDetail类，用于Spring Security的权限鉴定
 *
 * @author Smile_slime_47
 * @since 2023-09-03
 */
class LoginUser(val user: User) : UserDetails {
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    lateinit var groupMapper: GroupMapper

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        val authorities: MutableList<GrantedAuthority> = mutableListOf()
        val group = groupMapper.selectById(user.permissionGroup)
        if (group.permissionLogin == 1) {
            authorities.add(GrantedAuthority { "LOGIN" })
        }
        if (group.permissionRelease == 1) {
            authorities.add(GrantedAuthority { "RELEASE" })
        }
        if (group.permissionEdit == 1) {
            authorities.add(GrantedAuthority { "EDIT" })
        }
        if (group.permissionDelete == 1) {
            authorities.add(GrantedAuthority { "DELETE" })
        }
        if (group.permissionComment == 1) {
            authorities.add(GrantedAuthority { "COMMENT" })
        }
        if (group.permissionAdminMonit == 1) {
            authorities.add(GrantedAuthority { "ADMIN_MONIT" })
        }
        if (group.permissionAdminArticle == 1) {
            authorities.add(GrantedAuthority { "ADMIN_ARTICLE" })
        }
        if (group.permissionAdminUser == 1) {
            authorities.add(GrantedAuthority { "ADMIN_USER" })
        }
        return authorities
    }

    override fun getPassword(): String = user.password!!
    override fun getUsername(): String = user.userName!!
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}