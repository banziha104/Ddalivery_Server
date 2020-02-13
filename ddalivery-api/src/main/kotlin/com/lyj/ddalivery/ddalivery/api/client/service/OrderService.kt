package com.lyj.ddalivery.ddalivery.api.client.service

import com.lyj.ddalivery.ddalivery.api.client.repository.OrderGroupRepository
import com.lyj.ddalivery.ddalivery.api.client.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService @Autowired constructor(
        private val orderRepository: OrderRepository,
        private val orderGroupRepository: OrderGroupRepository
){

}