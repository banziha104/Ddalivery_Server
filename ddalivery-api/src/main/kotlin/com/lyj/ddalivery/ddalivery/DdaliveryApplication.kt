package com.lyj.ddalivery.ddalivery

import com.lyj.ddalivery.ddalivery.model.Test
import com.lyj.ddalivery.ddalivery.model.Test2
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DdaliveryApplication

fun main(args: Array<String>) {
	print(Test().test())
    print(Test2().test2())
	runApplication<DdaliveryApplication>(*args)
}
