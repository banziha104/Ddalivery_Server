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
            val orders : List<OrderCreate>
    ){
        fun toEntity() : Pair<OrderGroup,List<Order>> = OrderGroup(null,clientName, address, lat, long, totalPrice) to orders.map { it.toEntity() }
    }

    data class OrderCreate(
            val clientId : Long,
            val productId : Long,
            val quantity : Int
    )
    {
        fun toEntity() : Order = Order(null,clientId,productId,quantity)
    }
    data class Response(
            val orderGroupId : Long,
            val clientName : String,
            val address : String,
            val lat : Double,
            val long : Double,
            val totalPrice : Int,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            val orders : List<OrderResponse>? = null
    ){
        companion object{
            fun from(o: OrderGroup) : Response = o.run { Response(orderGroupId!!, clientName, address, lat, long, totalPrice, orders?.map { OrderResponse.from(it) }) }
        }
    }

    data class OrderResponse(
            val orderId : Long,
            val clientId : Long, // 주문자명
            val productId : Long, // 아이템명
            val quantity : Int
    ){
        companion object{
            fun from(o : Order) : OrderResponse= o.run { OrderResponse(orderId!!, clientId, productId, quantity) }
        }
    }
}