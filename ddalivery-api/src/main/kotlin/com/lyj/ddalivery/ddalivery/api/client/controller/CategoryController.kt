package com.lyj.ddalivery.ddalivery.api.client.controller

import com.lyj.ddalivery.ddalivery.api.client.dto.SellerDto
import com.lyj.ddalivery.ddalivery.api.client.service.CategoryService
import com.lyj.ddalivery.ddalivery.api.client.service.SellerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/category"])
class CategoryController @Autowired constructor(val categoryService: CategoryService)
{

}