package moe.saikyo47.service

import com.baomidou.mybatisplus.extension.service.IService
import moe.saikyo47.domain.entity.Article

interface ArticleService : IService<Article>{
    fun getArticleByParentCategory(ParentId:Long):List<Article>
}