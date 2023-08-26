package moe.saikyo47

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("moe.saikyo47.mapper")
class KtFrontstageApplication

fun main(args: Array<String>) {
    runApplication<KtFrontstageApplication>(*args)
}
