package com.lyj.ddalivery.ddalivery.api.client.service.impl

import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.api.client.dto.CategoryDto
import com.lyj.ddalivery.ddalivery.api.client.repository.CategoryRepository
import com.lyj.ddalivery.ddalivery.api.client.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl @Autowired constructor(
        private val categoryRepository: CategoryRepository
) : CategoryService {
    override fun findAll() : ApiResponse<List<CategoryDto.Response>> = ApiResponseFactory.createOK(categoryRepository.findAll().map { CategoryDto.Response.of(it,false) })
}