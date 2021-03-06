package com.lyj.ddalivery.ddalivery.api.client.service

import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.api.client.dto.CategoryDto
import com.lyj.ddalivery.ddalivery.api.client.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface CategoryService {
    fun findAll() : ApiResponse<List<CategoryDto.Response>>
}