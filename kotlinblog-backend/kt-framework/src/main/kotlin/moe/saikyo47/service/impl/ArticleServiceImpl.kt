package moe.saikyo47.service.impl

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import moe.saikyo47.domain.entity.Article
import moe.saikyo47.domain.entity.Category
import moe.saikyo47.mapper.ArticleMapper
import moe.saikyo47.service.ArticleService
import moe.saikyo47.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl : ServiceImpl<ArticleMapper, Article>(), ArticleService {
    @Autowired
    lateinit var categoryService: CategoryService

    /**
     * 根据父分类获取分类列表
     */
    override fun getArticleByParentCategory(parentId: Long): List<Article> {
        val categoryList = categoryService.getSubcategoryList(parentId)

        val articleWrapper: QueryWrapper<Article> = QueryWrapper<Article>()
        articleWrapper.eq("category", parentId)
        for (subcategory in categoryList) {
            articleWrapper.or().eq("category", subcategory.id)
        }

        return list(articleWrapper)
    }
}