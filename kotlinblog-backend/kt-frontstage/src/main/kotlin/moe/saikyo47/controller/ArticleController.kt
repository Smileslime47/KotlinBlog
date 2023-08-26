package moe.saikyo47.controller

import moe.saikyo47.domain.entity.Article
import moe.saikyo47.service.ArticleService
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

    @GetMapping("/all-by-parent")
    fun getAllByParent(path:String):List<Article>{
        System.out.println("test")
        val parentId=path.replace("/","").toLong()
        return articleService.getArticleByParentCategory(parentId)
    }

    @GetMapping("/list")
    fun list():List<Article>{
        return articleService.list()
    }

    @GetMapping("/test")
    fun test():String{
        return "hello!"
    }
}