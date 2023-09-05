package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mapper.UserMapper
import moe.saikyo47.domain.entity.User

import org.springframework.stereotype.Service;
import service.UserService

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-09-03 14:51:13
 */
@Service("userService")
class UserServiceImpl :  ServiceImpl<UserMapper, User>(), UserService
