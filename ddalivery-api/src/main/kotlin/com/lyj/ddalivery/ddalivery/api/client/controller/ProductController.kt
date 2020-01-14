package com.lyj.ddalivery.ddalivery.api.client.controller

import com.lyj.ddalivery.ddalivery.api.client.dto.ProductDto
import com.lyj.ddalivery.ddalivery.api.client.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*


/***
 * 프로덕트 관련
 */
@RestController
@RequestMapping(value = ["/api/product"])
class ProductController @Autowired constructor(
        private val productService: ProductService
) {
    @GetMapping
    fun getPage(pageable: Pageable, @RequestParam("seller") seller : Array<Long>) = productService.getProduct(pageable,seller)

    @PostMapping
    fun create(@RequestBody productDto: ProductDto.Create) = productService.createProduct(productDto)
}