package com.lyj.ddalivery.ddalivery.api.client.controller

import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.ddalivery.api.client.dto.OrderDto
import com.lyj.ddalivery.ddalivery.api.client.dto.OrderGroupDto
import com.lyj.ddalivery.ddalivery.api.client.service.CategoryService
import com.lyj.ddalivery.ddalivery.api.client.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/order"])
class OrderController @Autowired constructor(private val orderService: OrderService){

    @GetMapping("{orderGroupId}")
    fun getOrderGroup(@PathVariable("orderGroupId")orderGroupId : Long) : ApiResponse<*> = orderService.getOrderGroup(orderGroupId)

    @PostMapping
    fun createOrder(@RequestBody dto : OrderGroupDto.Create) = orderService.createOrder(dto)
}