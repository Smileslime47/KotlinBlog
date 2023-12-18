package moe.saikyo47.service.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import moe.saikyo47.constant.Constant
import moe.saikyo47.domain.entity.Article
import moe.saikyo47.mapper.ArticleMapper
import moe.saikyo47.service.ArticleService
import moe.saikyo47.service.CategoryService
import moe.saikyo47.utils.FileHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl : ServiceImpl<ArticleMapper, Article>(), ArticleService {
    @Autowired
    @Lazy
    lateinit var categoryService: CategoryService

    /**
     * 根据父分类及父分类下的子分类获取文章列表
     *
     * @param parentId 父分类ID
     * @return 分类直接为或直属于parentId下的文章列表
     */
    override fun getArticleByRootCategory(rootId: Long): List<Article> {
        //获取属于父分类ID下的全部子分类
        val categoryList = categoryService.getSubCategories(rootId)

        //获取父分类及全部子分类下的文章列表
        val articleWrapper = KtQueryWrapper(Article())
        articleWrapper.eq(Article::category, rootId)
        for (subcategory in categoryList) {
            articleWrapper.or().eq(Article::category, subcategory.id)
        }

        //返回文章列表
        return list(articleWrapper)
    }

    override fun getArticleByDirectCategory(categoryId: Long): List<Article> {
        //获取分类下的文章列表
        val articleWrapper = KtQueryWrapper(Article())
        articleWrapper.eq(Article::category, categoryId)
        //返回文章列表
        return list(articleWrapper)
    }

    override fun getArticleById(articleId: Long): Article {
        val articleWrapper = KtQueryWrapper(Article())
        articleWrapper.eq(Article::id, articleId)
        val article = getOne(articleWrapper)
        if (article.content?.startsWith("/") == true) {
            val content = FileHandler.getTextFile(Constant.Environment.RESOURCE_PATH + article.content)
            article.content = content
        }
        return article
    }
}