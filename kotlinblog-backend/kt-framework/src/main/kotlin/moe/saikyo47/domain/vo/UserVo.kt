package moe.saikyo47.domain.vo

data class UserVo (
    //用户名
    var userName: String? = null,
    //用户昵称
    var nickName: String? = null,
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
)