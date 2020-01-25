package com.lyj.ddalivery.ddalivery.api.client.service

import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.api.client.dto.SellerDto
import com.lyj.ddalivery.ddalivery.api.client.repository.SellerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
class SellerService(
        private val sellerRepository: SellerRepository
){

    fun findByDistance(latitude : Double,
                       longitude : Double,
                       limit : Int) = ApiResponseFactory.createOK(sellerRepository.findAllByDistance(latitude, longitude, limit).map { SellerDto.Response.of(it,false) })

    @Transactional
    fun create(dto : SellerDto.Create) : ApiResponse<*>{
        return ApiResponseFactory.DEFAULT_OK
    }
}