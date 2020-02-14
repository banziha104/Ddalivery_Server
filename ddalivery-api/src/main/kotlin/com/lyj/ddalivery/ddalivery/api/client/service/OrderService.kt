package com.lyj.ddalivery.ddalivery.api.client.service

import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.api.client.dto.OrderDto
import com.lyj.ddalivery.ddalivery.api.client.repository.OrderGroupRepository
import com.lyj.ddalivery.ddalivery.api.client.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class OrderService @Autowired constructor(
        private val orderRepository: OrderRepository,
        private val orderGroupRepository: OrderGroupRepository,
        private val entityManager : EntityManager
){

    fun createOrder(dto : OrderDto.Create) : ApiResponse<*>{
        val (group, orders) = dto.toEntity()
        val orderGroup =  orderGroupRepository.save(group)
        orders.forEach {
            it.orderGroup = orderGroup
            orderRepository.save(it)
        }

        return ApiResponseFactory.DEFAULT_OK
    }
}