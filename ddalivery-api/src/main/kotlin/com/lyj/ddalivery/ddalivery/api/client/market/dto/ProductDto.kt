package com.lyj.ddalivery.ddalivery.api.client.market.dto

import com.lyj.ddalivery.ddalivery.entity.Product
import com.thinkinglogic.builder.annotation.Builder
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id

class ProductDto {
    data class Create(
            val name: String,
            var image: String,
            val price: Int,
            val recommendedRateCount: Int,
            val recommendedUserCount: Int,
            val seller: String
    ) {
        fun toEntity() : Product = Product(null,name,image,price,recommendedRateCount, recommendedUserCount, seller)
    }
}