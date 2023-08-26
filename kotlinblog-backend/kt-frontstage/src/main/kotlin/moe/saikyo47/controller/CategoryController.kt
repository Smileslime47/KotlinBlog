package moe.saikyo47.controller

import moe.saikyo47.domain.entity.Category
import moe.saikyo47.domain.entity.ResponseResult
import moe.saikyo47.domain.vo.CategoryVo
import moe.saikyo47.enums.AppHttpCodeEnum
import moe.saikyo47.service.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import moe.saikyo47.service.CategoryService
import moe.saikyo47.utils.BeanCopyUtils
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping

@RestController
@CrossOrigin
@RequestMapping("/category")
class CategoryController {
    @Autowired
    lateinit var categoryService: CategoryService

    @GetMapping("/parent")
    fun getParentCategories():ResponseResult<List<CategoryVo>>{
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanListCopy(categoryService.getParentCategoryList(),CategoryVo::class.java)
        )
    }

    @GetMapping("/subcategory")
    fun getSubcategories(parentId:Long):ResponseResult<List<CategoryVo>>{
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanListCopy(categoryService.getSubcategoryList(parentId),CategoryVo::class.java)
        )
    }

}