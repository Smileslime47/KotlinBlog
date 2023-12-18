package moe.saikyo47.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName

/**
 * (Group)表实体类
 *
 * @author makejava
 * @since 2023-09-03 14:53:25
 */
@TableName("Group")
data class Group(
    //权限组id
    var id: Long? = null,
    //权限组名
    var groupName: String? = null,
    //权限优先级，当涉及到越权操作时，仅能操作优先级小于自己的用户
    var priority: Int? = null,
    //登录权限
    var permissionLogin: Int? = null,
    //发布文章权限
    var permissionRelease: Int? = null,
    //编辑文章权限
    var permissionEdit: Int? = null,
    //删除文章权限
    var permissionDelete: Int? = null,
    //评论权限
    var permissionComment: Int? = null,
    //监控权限
    var permissionAdminMonit: Int? = null,
    //文章管理权限，包括越权修改文章、置顶等
    var permissionAdminArticle: Int? = null,
    //用户管理权限，可以越权修改用户信息等
    var permissionAdminUser: Int? = null,
    //逻辑删除
    var delFlag: Int? = null,
)

