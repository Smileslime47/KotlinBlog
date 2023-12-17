package moe.saikyo47.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.saikyo47.domain.entity.Group
import moe.saikyo47.mapper.GroupMapper
import org.springframework.stereotype.Service;
import moe.saikyo47.service.GroupService

/**
 * (Group)表服务实现类
 *
 * @author makejava
 * @since 2023-09-03 14:53:04
 */
@Service("groupService")
class GroupServiceImpl :  ServiceImpl<GroupMapper, Group>(), GroupService
