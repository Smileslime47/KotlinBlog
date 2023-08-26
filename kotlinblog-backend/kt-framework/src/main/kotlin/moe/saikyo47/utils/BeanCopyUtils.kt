package moe.saikyo47.utils

import org.springframework.beans.BeanUtils

class BeanCopyUtils {
    companion object{
        fun <T : Any> beanCopy(source: Any, clazz:Class<T>):T{
            val result=clazz.getConstructor().newInstance()
            BeanUtils.copyProperties(source,result)
            return result
        }

        fun <T : Any> beanListCopy(source:List<Any>, clazz:Class<T>):List<T>{
            return source
                .map { obj -> beanCopy(obj,clazz) }
                .toList()
        }
    }
}