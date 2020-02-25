package com.lyj.ddalivery.ddalivery.api.client.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.lyj.ddalivery.ddalivery.entity.Order
import com.lyj.ddalivery.ddalivery.entity.OrderGroup
import javax.persistence.Column

class OrderGroupDto{
    data class Create(
            val clientId : Int,
            val clientName : String,
            val address : String,
            val lat : Double,
            val long : Double,
            var isMatched: Boolean,
            var isCompleted: Boolean,
            val orders : List<OrderDto.Create>
    ){
        fun toEntity() : Pair<OrderGroup,List<Order>> = OrderGroup(null,clientId,clientName, address, lat, long,isMatched, isCompleted) to orders.map { it.toEntity() }
    }


    data class Response(
            val orderGroupId : Long,
            val clientId : Int,
            val clientName : String,
            val address : String,
            val lat : Double,
            val long : Double,
            var isMatched: Boolean,
            var isCompleted: Boolean,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            val orders : List<OrderDto.Response>? = null
    ){
        companion object{
            fun from(o: OrderGroup) : Response = o.run { Response(orderGroupId!!,clientId,clientName, address, latitude, longitude, isMatched, isCompleted, orders?.map { OrderDto.Response.from(it) }) }
        }
    }

}