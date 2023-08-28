package moe.saikyo47.controller

import moe.saikyo47.domain.entity.Article
import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.vo.ArticleBriefVo
import moe.saikyo47.domain.vo.ArticleDetailedVo
import moe.saikyo47.enums.AppHttpCodeEnum
import moe.saikyo47.service.ArticleService
import moe.saikyo47.utils.BeanCopyUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/article")
class ArticleController {
    @Autowired
    lateinit var articleService: ArticleService

    /**
     * 根据分类获取对应分类下的全部文章
     */
    @GetMapping("/all-by-parent")
    fun getAllByParent(path: String): ResponseResult<List<ArticleBriefVo>> {
        val parentId = path.replace("/", "").toLong()
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanListCopy(articleService.getArticleByDeepCategory(parentId), ArticleBriefVo::class.java)
        )
    }

    @GetMapping("/all-by-subcategory")
    fun getAllBySubCategory(categoryId: Long): ResponseResult<List<ArticleBriefVo>> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanListCopy(articleService.getArticleByDirectCategory(categoryId), ArticleBriefVo::class.java)
        )
    }

    @GetMapping("/info-by-id")
    fun getInfoById(articleId: Long): ResponseResult<ArticleBriefVo> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanCopy(articleService.getArticleById(articleId), ArticleBriefVo::class.java)
        )
    }

    @GetMapping("/article-by-id")
    fun getArticleById(articleId: Long): ResponseResult<ArticleDetailedVo> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
                BeanCopyUtils.beanCopy(articleService.getArticleById(articleId), ArticleDetailedVo::class.java)
        )
    }

    @GetMapping("/list")
    fun list(): List<Article> {
        return articleService.list()
    }
}