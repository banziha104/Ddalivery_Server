package com.lyj.ddalivery.ddalivery

import com.lyj.ddalivery.ddalivery.model.Test
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DdaliveryApplication

fun main(args: Array<String>) {
	print(Test().test())
	runApplication<DdaliveryApplication>(*args)
}
