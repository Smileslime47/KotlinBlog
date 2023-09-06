package moe.saikyo47.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import moe.saikyo47.constant.Constant
import moe.saikyo47.domain.entity.Category
import moe.saikyo47.mapper.CategoryMapper
import moe.saikyo47.service.ArticleService
import moe.saikyo47.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

/**
 * 分类表(Category)表服务实现类
 *
 * @author smile_slime_47
 * @since 2023-07-23
 */
@Service
class CategoryServiceImpl : ServiceImpl<CategoryMapper, Category>(), CategoryService {
    /**
     * 获取父分类列表
     *
     * @return 父分类Id为-1（即无父分类）的全部分类列表
     */
    override fun getRootCategories(): List<Category> {
        val categoryWrapper = KtQueryWrapper(Category())
        //从分类表中获取有效的分类，且无上级父分类
        categoryWrapper
            .eq(Category::isAvailable, Constant.CATEGORY_IS_AVAILABILE)
            .eq(Category::parent, (-1).toLong())

        return list(categoryWrapper)
    }

    /**
     * 根据父分类获取对应的子分类列表
     *
     * @param parentId 父分类Id
     * @return 父分类为parentId的全部分类列表
     */
    override fun getSubCategories(parentId: Long): List<Category> {
        val categoryWrapper = KtQueryWrapper(Category())
        //从分类表中获取有效的分类，且上级父分类为指定父分类ID
        categoryWrapper
            .eq(Category::isAvailable, Constant.CATEGORY_IS_AVAILABILE)
            .eq(Category::parent, parentId)

        return list(categoryWrapper)
    }

    override fun getRootCategory(categoryId: Long): Category {
        var categoryWrapper = KtQueryWrapper(Category()).eq(Category::id, categoryId)
        var category: Category = getOne(categoryWrapper)

        while (category.parent != (-1).toLong()) {
            categoryWrapper = KtQueryWrapper(Category()).eq(Category::id, category.parent)
            category = getOne(categoryWrapper)
        }

        return category
    }
}
