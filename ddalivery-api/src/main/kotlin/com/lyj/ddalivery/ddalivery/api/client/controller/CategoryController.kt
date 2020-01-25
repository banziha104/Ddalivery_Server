package com.lyj.ddalivery.ddalivery.api.client.controller

import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.ddalivery.api.client.dto.CategoryDto
import com.lyj.ddalivery.ddalivery.api.client.dto.SellerDto
import com.lyj.ddalivery.ddalivery.api.client.service.CategoryService
import com.lyj.ddalivery.ddalivery.api.client.service.SellerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/category"])
class CategoryController @Autowired constructor(private val categoryService: CategoryService) {
    @GetMapping
    fun findAll() : ApiResponse<List<CategoryDto.Response>> = categoryService.findAll()
}