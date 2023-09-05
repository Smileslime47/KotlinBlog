package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Group
import mapper.GroupMapper
import org.springframework.stereotype.Service;
import service.GroupService

/**
 * (Group)表服务实现类
 *
 * @author makejava
 * @since 2023-09-03 14:53:04
 */
@Service("groupService")
class GroupServiceImpl :  ServiceImpl<GroupMapper, Group>(), GroupService
