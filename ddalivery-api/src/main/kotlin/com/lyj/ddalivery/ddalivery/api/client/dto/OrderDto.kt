package com.lyj.ddalivery.ddalivery.api.client.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.lyj.ddalivery.ddalivery.entity.Order
import com.lyj.ddalivery.ddalivery.entity.OrderGroup
import javax.persistence.*

class OrderDto{
    data class Create(
            val clientName : String,
            val address : String,
            val lat : Double,
            val long : Double,
            val totalPrice : Int,
            val orders : List<Order>
    ){

    }

    data class Response(
            val orderGroupId : Long,
            val clientName : String,
            val address : String,
            val lat : Double,
            val long : Double,
            val totalPrice : Int,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            val orders : List<Order>? = null
    ){
        companion object{
            fun from(o: OrderGroup) : Response = o.run { Response(orderGroupId, clientName, address, lat, long, totalPrice) }
        }
    }
}