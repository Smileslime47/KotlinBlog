package moe.saikyo47.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName
import java.util.*

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-09-03 14:49:25
 */
@TableName("User")
data class User(
    //用户主键
    var id: Long? = null,
    //用户名
    var userName: String? = null,
    //用户昵称
    var nickName: String? = null,
    //用户密码
    var password: String? = null,
    //用户权限组
    var permissionGroup: Long? = null,
    //用户邮箱
    var email: String? = null,
    //用户手机
    var phoneNumber: String? = null,
    //用户性别
    var sex: String? = null,
    //用户头像
    var avatar: String? = null,
    //创建时间
    var createTime: Date? = null,
    //逻辑删除
    var delFlag: Int? = null,
)

