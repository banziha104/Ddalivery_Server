package com.lyj.ddalivery.ddalivery.api.client.market.dto

import com.lyj.ddalivery.ddalivery.entity.Category
import com.lyj.ddalivery.ddalivery.entity.Product
import com.lyj.ddalivery.ddalivery.entity.Seller
import com.thinkinglogic.builder.annotation.Builder
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id

class ProductDto {
    data class Create(
            val name: String,
            var image: String,
            val description : String,
            val price: Int,
            val recommendedRateCount: Int,
            val recommendedUserCount: Int,
            val seller: Long,
            val category : Long
    ) {
        fun toEntity(seller: Seller,category: Category) : Product = Product(null,name,image,description,price,recommendedRateCount, recommendedUserCount,seller, category)
    }
}