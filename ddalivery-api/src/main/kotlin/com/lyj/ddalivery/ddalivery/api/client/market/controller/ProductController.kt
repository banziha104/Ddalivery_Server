package com.lyj.ddalivery.ddalivery.api.client.market.controller

import com.lyj.ddalivery.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.ddalivery.api.response.ApiResponseFactory
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/product"])
@Slf4j
class ProductController {
    @GetMapping("{message}")
    fun getThis(@PathVariable("message") message : String) : ApiResponse<ABC<Int>>{
        val test = ApiResponseFactory.createOK(ABCF<Int>().getDefault())
        print(test)
//        return ApiResponseFactory.createOK(message)
        return test
    }
}

class ABC<T>{
    var a : String? = null
    var b : String? = null
    var c : T? = null
}

class ABCF<T>{
    fun getDefault() : ABC<T>{
        val abc = ABC<T>()
        abc.a = "a"
        return abc
    }
}
