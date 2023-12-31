package moe.saikyo47.domain.vo

data class CategoryVo(
    //分类主键
    var id: Long? = null,
    //分类名
    var name: String? = null,
    //分类父ID
    var parent: Long? = null,
    //分类描述
    var description: String? = null,
)

