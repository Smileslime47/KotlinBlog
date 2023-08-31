package moe.saikyo47.service

import com.baomidou.mybatisplus.extension.service.IService
import moe.saikyo47.domain.entity.Article

interface ArticleService : IService<Article> {
    fun getArticleByRootCategory(parentId: Long): List<Article>
    fun getArticleByDirectCategory(categoryId: Long): List<Article>
    fun getArticleById(articleId: Long): Article
}