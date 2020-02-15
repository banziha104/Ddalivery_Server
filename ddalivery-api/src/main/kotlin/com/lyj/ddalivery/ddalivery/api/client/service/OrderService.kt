package com.lyj.ddalivery.ddalivery.api.client.service

import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.api.client.dto.OrderDto
import com.lyj.ddalivery.ddalivery.api.client.dto.OrderGroupDto
import com.lyj.ddalivery.ddalivery.api.client.repository.OrderGroupRepository
import com.lyj.ddalivery.ddalivery.api.client.repository.OrderRepository
import com.lyj.ddalivery.ddalivery.entity.OrderGroup
import com.lyj.ddalivery.ddalivery.exception.order.OrderGroupNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
class OrderService @Autowired constructor(
        private val orderRepository: OrderRepository,
        private val orderGroupRepository: OrderGroupRepository,
        private val entityManager : EntityManager
){
    fun getOrderGroup(orderGroupId : Long) : ApiResponse<*> {
        val result = orderGroupRepository.findById(orderGroupId)
        return if (result.isEmpty) ApiResponseFactory.createException(OrderGroupNotFoundException())
        else ApiResponseFactory.createOK(OrderGroupDto.Response.from(result.get()))
    }




    @Transactional
    fun createOrder(dto : OrderGroupDto.Create) : ApiResponse<*>{
        val (group, orders) = dto.toEntity()
        val orderGroup =  orderGroupRepository.save(group)
        orders.forEach {
            it.orderGroup = orderGroup
            orderRepository.save(it)
        }

        return ApiResponseFactory.DEFAULT_OK
    }
}