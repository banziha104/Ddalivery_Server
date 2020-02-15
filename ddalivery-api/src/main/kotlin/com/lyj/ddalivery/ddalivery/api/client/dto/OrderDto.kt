package com.lyj.ddalivery.ddalivery.api.client.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.lyj.ddalivery.ddalivery.entity.Order
import com.lyj.ddalivery.ddalivery.entity.OrderGroup
import javax.persistence.*

class OrderDto{

    data class Create(
            val productId : Long,
            val quantity : Int
    )
    {
        fun toEntity() : Order = Order(null,productId,quantity)
    }


    data class Response(
            val orderId : Long,
            val productId : Long, // 아이템명
            val quantity : Int
    ){
        companion object{
            fun from(o : Order) : Response= o.run { Response(orderId!!, productId, quantity) }
        }
    }
}