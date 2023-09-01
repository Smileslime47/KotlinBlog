package moe.saikyo47.service

import com.baomidou.mybatisplus.extension.service.IService;
import moe.saikyo47.domain.entity.Category

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-07-23 15:03:46
 */
interface CategoryService : IService<Category>{
    fun getRootCategories(): List<Category>
    fun getSubCategories(parentId: Long): List<Category>
    fun getRootCategory(categoryId:Long):Category
}
