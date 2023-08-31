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

@RestController
@CrossOrigin
@RequestMapping("/api/article")
class ArticleController {
    @Autowired
    lateinit var articleService: ArticleService

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

    @GetMapping("/info/article")
    fun getInfoById(id: Long): ResponseResult<ArticleBriefVo> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanCopy(articleService.getArticleById(id), ArticleBriefVo::class.java)
        )
    }

    @GetMapping("/detail/article")
    fun getDetailById(id: Long): ResponseResult<ArticleDetailVo> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanCopy(articleService.getArticleById(id), ArticleDetailVo::class.java)
        )
    }
}