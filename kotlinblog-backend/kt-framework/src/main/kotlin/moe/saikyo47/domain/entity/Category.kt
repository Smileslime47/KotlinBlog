package moe.saikyo47.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.util.*

@TableName("Category")
data class Category(
    //分类主键
    @TableId
    var id: Long? = null,
    //分类名
    var name: String? = null,
    //分类父ID
    var parent: Long? = null,
    //分类描述
    var description: String? = null,
    //是否有效
    var isAvailable: Int? = null,
    //创建人
    var createBy: Long? = null,
    //创建时间
    var createTime: Date? = null,
    //更新人
    var updateBy: Long? = null,
    //更新时间
    var updateTime: Date? = null,
    //逻辑删除字段
    var delFlag: Int? = null,
)

