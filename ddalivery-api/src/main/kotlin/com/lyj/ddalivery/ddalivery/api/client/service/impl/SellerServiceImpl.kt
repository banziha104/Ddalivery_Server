package com.lyj.ddalivery.ddalivery.api.client.service.impl

import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.api.client.dto.SellerDto
import com.lyj.ddalivery.ddalivery.api.client.repository.SellerRepository
import com.lyj.ddalivery.ddalivery.api.client.service.SellerService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SellerServiceImpl(
        private val sellerRepository: SellerRepository
) : SellerService{

    override fun findByDistance(latitude : Double,
                       longitude : Double,
                       limit : Int) = ApiResponseFactory.createOK(sellerRepository.findAllByDistance(latitude, longitude, limit).map { SellerDto.Response.of(it,false) })

    @Transactional
    override fun create(dto : SellerDto.Create) : ApiResponse<*> {
        return ApiResponseFactory.DEFAULT_OK
    }
}