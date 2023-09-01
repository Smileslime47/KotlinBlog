package moe.saikyo47.domain.vo

import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDate

data class ArticleDetailedVo(
    //文章主键
    var id: Long? = null,
    //文章标题
    var title: String? = null,
    //文章内容
    var content: String? = null,
    //文章摘要
    var summary: String? = null,
    //文章分类
    var category: Long? = null,
    //是否置顶
    var isTop: Int? = null,
    //是否发布
    var isReleased: Int? = null,
    //是否允许评论
    var isComment: Int? = null,
    //文章浏览量
    var viewCount: Long? = null,
    //发布人
    var createBy: Long? = null,
    //发布时间
    var createTime: LocalDate? = null,
    //更新人
    var updateBy: Long? = null,
    //更新时间
    var updateTime: LocalDate? = null,
)