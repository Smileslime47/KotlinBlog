package moe.saikyo47.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import mapper.UserMapper
import moe.saikyo47.constant.Constant
import moe.saikyo47.domain.dto.LoginUser
import moe.saikyo47.domain.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.Objects

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

    override fun loadUserByUsername(username: String?): UserDetails {
        val userWrapper = QueryWrapper<User>()
        userWrapper.eq(Constant.DataField.USER_USER_NAME, username)
        val user = userMapper.selectOne(userWrapper)

        if (Objects.isNull(user)) {
            throw RuntimeException("用户不存在")
        }

        return LoginUser(user)
    }
}