package moe.saikyo47.utils

import org.springframework.beans.BeanUtils

/**
 * Spring Bean类型转换工具类
 *
 * @author Smile_slime_47
 * @since 2023-08-26
 */
class BeanCopyUtils {
    companion object {
        /**
         * 将传入的对象拷贝并转换为目标类型
         *
         * @param source 被拷贝对象
         * @param clazz 拷贝后的目标类
         */
        fun <T : Any> beanCopy(source: Any, clazz: Class<T>): T {
            val result = clazz.getConstructor().newInstance()
            //按照字段的名称匹配原则拷贝数据
            BeanUtils.copyProperties(source, result)
            return result
        }

        /**
         * 将传入的对象列表拷贝并将元素转换为目标类型
         *
         * @param source 被拷贝列表
         * @param clazz 列表中元素拷贝后的目标类
         */
        fun <T : Any> beanListCopy(source: List<Any>, clazz: Class<T>): List<T> {
            return if (source.isEmpty())
            //源列表为空时返回空列表
                ArrayList()
            else source
                .map { obj -> beanCopy(obj, clazz) }
                .toList()
        }
    }
}