package com.lyj.ddalivery.ddalivery.api.client.controller

import com.lyj.ddalivery.ddalivery.api.client.dto.OrderDto
import com.lyj.ddalivery.ddalivery.api.client.service.CategoryService
import com.lyj.ddalivery.ddalivery.api.client.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/order"])
class OrderController @Autowired constructor(private val orderService: OrderService){

    @PostMapping
    fun createOrder(@RequestBody dto : OrderDto.Create) = orderService.createOrder(dto)
}