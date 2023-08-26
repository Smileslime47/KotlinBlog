package moe.saikyo47.domain.vo

import com.baomidou.mybatisplus.annotation.TableId

data class CategoryVo(
    //分类主键
    @TableId
    var id: Long? = null,
    //分类名
    var name: String? = null,
    //分类父ID
    var parent: Long? = null,
    //分类描述
    var description: String? = null,
)

