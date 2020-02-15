package com.lyj.ddalivery.ddalivery.api.client.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.lyj.ddalivery.ddalivery.entity.Order
import com.lyj.ddalivery.ddalivery.entity.OrderGroup

class OrderGroupDto{
    data class Create(
            val clientName : String,
            val address : String,
            val lat : Double,
            val long : Double,
            val totalPrice : Int,
            val orders : List<OrderDto.Create>
    ){
        fun toEntity() : Pair<OrderGroup,List<Order>> = OrderGroup(null,clientName, address, lat, long, totalPrice) to orders.map { it.toEntity() }
    }


    data class Response(
            val orderGroupId : Long,
            val clientName : String,
            val address : String,
            val lat : Double,
            val long : Double,
            val totalPrice : Int,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            val orders : List<OrderDto.Response>? = null
    ){
        companion object{
            fun from(o: OrderGroup) : Response = o.run { Response(orderGroupId!!, clientName, address, latitude, longitude, totalPrice, orders?.map { OrderDto.Response.from(it) }) }
        }
    }

}