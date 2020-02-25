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

interface OrderService{
    fun getOrderGroup(orderGroupId : Array<Long>) : ApiResponse<*>
    fun createOrder(dto : OrderGroupDto.Create) : ApiResponse<*>
}