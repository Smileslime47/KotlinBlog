package moe.saikyo47

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("moe.saikyo47.mapper")
class KtFrontstageApplication

/**
 * 前台入口类
 *
 * @author Smile_slime_47
 * @since 2023-08-26
 */
fun main(args: Array<String>) {
    runApplication<KtFrontstageApplication>(*args)
}
