package moe.saikyo47.controller

import moe.saikyo47.domain.entity.Article
import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.vo.ArticleBriefVo
import moe.saikyo47.domain.vo.ArticleDetailVo
import moe.saikyo47.enums.AppHttpCodeEnum
import moe.saikyo47.service.ArticleService
import moe.saikyo47.utils.BeanCopyUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Article Controller类
 *
 * @author Smile_slime_47
 * @since 2023-08-26
 */
@RestController
@CrossOrigin
@RequestMapping("/api/article")
class ArticleController {
    @Autowired
    lateinit var articleService: ArticleService

    /**
     * 根据分类获取分类下文章的简要信息
     *
     * 接口：/info/category
     * 
     * @param id 分类ID
     * @param deep 是否搜索该分类下所有子分类的文章，若为false，则只搜索文章中所属分类ID为id的文章
     */
    @GetMapping("/info/category")
    fun getInfoByCategory(id: Long, deep: Boolean): ResponseResult<List<ArticleBriefVo>> {
        val searchStrategy: (id: Long) -> List<Article> =
            if (deep)
                articleService::getArticleByRootCategory
            else
                articleService::getArticleByDirectCategory
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanListCopy(searchStrategy(id), ArticleBriefVo::class.java)
        )
    }

    /**
     * 根据文章ID获取文章的简要信息
     *
     * 接口：/info/article
     * 
     * @param id 文章ID
     */
    @GetMapping("/info/article")
    fun getInfoById(id: Long): ResponseResult<ArticleBriefVo> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanCopy(articleService.getArticleById(id), ArticleBriefVo::class.java)
        )
    }

    /**
     * 根据文章ID获取文章的详细信息，包括文章内容
     *
     * 接口：/detail/article
     * 
     * @param id 文章ID
     */
    @GetMapping("/detail/article")
    fun getDetailById(id: Long): ResponseResult<ArticleDetailVo> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanCopy(articleService.getArticleById(id), ArticleDetailVo::class.java)
        )
    }
}