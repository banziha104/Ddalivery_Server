package com.lyj.ddalivery.ddalivery.api.client.service

import com.lyj.ddalivery.ddalivery.api.client.dto.ProductDto
import com.lyj.ddalivery.ddalivery.api.client.repository.ProductRepository
import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.api.client.repository.CategoryRepository
import com.lyj.ddalivery.ddalivery.api.client.repository.SellerRepository
import com.lyj.ddalivery.ddalivery.config.ImagePathConfig
import com.lyj.ddalivery.ddalivery.exception.product.CategoryNotFoundException
import com.lyj.ddalivery.ddalivery.exception.product.FileNotSaveException
import com.lyj.ddalivery.ddalivery.exception.product.SellerNotFoundException
import javassist.NotFoundException
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestParam
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermission
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.persistence.EntityManager


interface ProductService {
    fun getProductBySeller(pageable: Pageable, seller: Array<Long>): ApiResponse<*>
    fun getProductByLocation(pageable: Pageable, latitude: Double, longitude: Double, limit: Int): ApiResponse<*>
    fun createProduct(dto: ProductDto.Create): ApiResponse<*>
}