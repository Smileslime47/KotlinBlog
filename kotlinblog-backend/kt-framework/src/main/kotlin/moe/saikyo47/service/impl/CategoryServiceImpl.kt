package moe.saikyo47.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
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
    @Autowired
    @Lazy
    lateinit var articleService: ArticleService

    /**
     * 获取父分类列表
     *
     * @return 父分类Id为-1（即无父分类）的全部分类列表
     */
    override fun getRootCategories(): List<Category> {
        val categoryWrapper: QueryWrapper<Category> = QueryWrapper()
        //从分类表中获取有效的分类，且无上级父分类
        categoryWrapper.eq(Constant.DataField.CATEGORY_IS_AVAILABLE, Constant.CATEGORY_IS_AVAILABILE).eq("parent", (-1).toLong())

        return list(categoryWrapper)
    }

    /**
     * 根据父分类获取对应的子分类列表
     *
     * @param parentId 父分类Id
     * @return 父分类为parentId的全部分类列表
     */
    override fun getSubCategories(parentId: Long): List<Category> {
        val categoryWrapper: QueryWrapper<Category> = QueryWrapper()
        //从分类表中获取有效的分类，且上级父分类为指定父分类ID
        categoryWrapper
            .eq(Constant.DataField.CATEGORY_IS_AVAILABLE, Constant.CATEGORY_IS_AVAILABILE)
            .eq(Constant.DataField.CATEGORY_PARENT,parentId)

        return list(categoryWrapper)
    }

    override fun getRootCategory(categoryId: Long): Category {
        var cid = categoryId
        var categoryWrapper: QueryWrapper<Category> = QueryWrapper()
        categoryWrapper.eq(Constant.DataField.CATEGORY_ID, categoryId)
        var category: Category = getOne(categoryWrapper)

        while (category.parent != (-1).toLong()) {
            categoryWrapper = QueryWrapper()
            categoryWrapper.eq(Constant.DataField.CATEGORY_ID, category.parent)
            category = getOne(categoryWrapper)
        }

        return category
    }
}
