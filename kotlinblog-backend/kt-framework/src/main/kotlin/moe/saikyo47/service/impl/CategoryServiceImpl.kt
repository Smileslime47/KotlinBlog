package moe.saikyo47.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.saikyo47.mapper.CategoryMapper
import moe.saikyo47.constant.Constant
import moe.saikyo47.domain.entity.Category
import org.springframework.stereotype.Service;
import moe.saikyo47.service.CategoryService

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-07-23 15:03:46
 */
@Service
class CategoryServiceImpl : ServiceImpl<CategoryMapper, Category>(), CategoryService {
    /**
     * 获取父分类列表
     */
    override fun getParentCategoryList(): List<Category> {
        //从分类表中获取有效的分类，且无上级父分类
        return list().filter { category ->
            category.isAvailable == Constant.CATEGORY_IS_AVAILABILE && category.parent == (-1).toLong()
        }
    }

    /**
     * 根据父分类获取对应的子分类列表
     */
    override fun getSubcategoryList(parentId: Long): List<Category> {
        //从分类表中获取有效的分类，且上级父分类为指定父分类ID
        return list().filter { category ->
            category.isAvailable == Constant.CATEGORY_IS_AVAILABILE && category.parent == parentId
        }
    }
}
