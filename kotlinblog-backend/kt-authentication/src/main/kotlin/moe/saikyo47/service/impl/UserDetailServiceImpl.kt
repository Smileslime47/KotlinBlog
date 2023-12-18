package moe.saikyo47.service.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import moe.saikyo47.domain.dto.LoginUser
import moe.saikyo47.domain.entity.Group
import moe.saikyo47.domain.entity.User
import moe.saikyo47.mapper.GroupMapper
import moe.saikyo47.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

/**
 * UserDetail Service实现类
 * 用于覆盖Spring Security的默认实现类
 * 在Database中获取用户信息并授权
 *
 * @author Smile_slime_47
 * @since 2023-09-03
 */
@Service
class UserDetailServiceImpl : UserDetailsService {
    @Autowired
    @Lazy
    lateinit var userMapper: UserMapper

    @Autowired
    @Lazy
    lateinit var groupMapper: GroupMapper

    override fun loadUserByUsername(username: String?): UserDetails {
        val userWrapper = KtQueryWrapper(User::class.java)
        userWrapper.eq(User::userName, username)
        val user = userMapper.selectOne(userWrapper)

        if (Objects.isNull(user)) {
            throw RuntimeException("用户不存在")
        }

        val groupWrapper = KtQueryWrapper(Group::class.java)
        groupWrapper.eq(Group::id, user.permissionGroup)
        val group = groupMapper.selectOne(groupWrapper)

        return LoginUser(user, group)
    }
}