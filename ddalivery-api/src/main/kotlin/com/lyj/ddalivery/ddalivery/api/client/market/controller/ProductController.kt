package com.lyj.ddalivery.ddalivery.api.client.market.controller

import com.lyj.ddalivery.ddalivery.api.client.market.dto.ProductDto
import com.lyj.ddalivery.ddalivery.api.client.market.service.ProductService
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
    fun getPage(pageable: Pageable) = productService.getProduct(pageable)

    @PostMapping
    fun create(@RequestBody productDto: ProductDto.Create) = productService.createProduct(productDto)
}