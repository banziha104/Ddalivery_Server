package com.lyj.ddalivery.ddalivery.api.client.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.lyj.ddalivery.ddalivery.entity.Order
import com.lyj.ddalivery.ddalivery.entity.OrderGroup
import javax.persistence.*

class OrderDto {

    data class Create(
            val productId: Long,
            val productName: String,
            val image: String,
            val description: String,
            val price: Int,
            val quantity: Int,
            val sellerName: String,
            val sellerAddress: String,
            val categoryName: String
            ) {
        fun toEntity(): Order = Order(null, productId, productName, image, description, price, quantity,sellerName, sellerAddress, categoryName)
    }


    data class Response(
            val orderId: Long,
            val productId: Long,
            val productName: String,
            val image: String,
            val description: String,
            val price: Int,
            val quantity: Int,
            val sellerName: String,
            val sellerAddress: String,
            val categoryName: String
    ) {
        companion object {
            fun from(o: Order): Response = o.run { Response(orderId!!, productId, productName, image, description, price, quantity,sellerName, sellerAddress, categoryName) }
        }
    }
}