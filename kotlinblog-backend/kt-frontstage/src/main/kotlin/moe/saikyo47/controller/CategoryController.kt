package moe.saikyo47.controller

import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.vo.CategoryVo
import moe.saikyo47.enums.AppHttpCodeEnum
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import moe.saikyo47.service.CategoryService
import moe.saikyo47.utils.BeanCopyUtils
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping

@RestController
@CrossOrigin
@RequestMapping("/api/category")
class CategoryController {
    @Autowired
    lateinit var categoryService: CategoryService

    /**
     * 获取所有父级分类
     */
    @GetMapping("/root-categories")
    fun getRootCategories(): ResponseResult<List<CategoryVo>> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanListCopy(categoryService.getRootCategories(), CategoryVo::class.java)
        )
    }

    /**
     * 获取所有子级分类
     */
    @GetMapping("/sub-categories")
    fun getSubCategories(id: Long): ResponseResult<List<CategoryVo>> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanListCopy(categoryService.getSubCategories(id), CategoryVo::class.java)
        )
    }

    /**
     * 获取该分类的根分类
     */
    @GetMapping("/root-category")
    fun getRootCategory(id:Long): ResponseResult<CategoryVo> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanCopy(categoryService.getRootCategory(id), CategoryVo::class.java)
        )
    }
}