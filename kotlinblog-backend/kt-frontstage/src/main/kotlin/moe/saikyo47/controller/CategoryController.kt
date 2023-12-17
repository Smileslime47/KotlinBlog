package moe.saikyo47.controller

import moe.saikyo47.constant.Constant
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

/**
 * Category Controller类
 *
 * @author Smile_slime_47
 * @since 2023-08-26
 */
@RestController
@CrossOrigin
@RequestMapping(Constant.ApiPath.CATEGORY_API)
class CategoryController {
    @Autowired
    lateinit var categoryService: CategoryService

    /**
     * 获取所有父级分类
     *
     * 接口：/root-categories
     */
    @GetMapping(Constant.ApiPath.CATEGORY_ROOT_CATEGORIES)
    fun getRootCategories(): ResponseResult<List<CategoryVo>> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanListCopy(categoryService.getRootCategories(), CategoryVo::class.java)
        )
    }

    /**
     * 获取所有子级分类
     *
     * 接口：/sub-categories
     */
    @GetMapping(Constant.ApiPath.CATEGORY_SUB_CATEGORIES)
    fun getSubCategories(id: Long): ResponseResult<List<CategoryVo>> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanListCopy(categoryService.getSubCategories(id), CategoryVo::class.java)
        )
    }

    /**
     * 获取该分类的根分类
     *
     * 接口：/root-category
     */
    @GetMapping(Constant.ApiPath.CATEGORY_ROOT_CATEGORY)
    fun getRootCategory(id: Long): ResponseResult<CategoryVo> {
        return ResponseResult(
            AppHttpCodeEnum.SUCCESS,
            BeanCopyUtils.beanCopy(categoryService.getRootCategory(id), CategoryVo::class.java)
        )
    }
}