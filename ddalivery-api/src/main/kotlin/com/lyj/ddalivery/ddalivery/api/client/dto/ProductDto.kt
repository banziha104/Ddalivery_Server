package com.lyj.ddalivery.ddalivery.api.client.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.lyj.ddalivery.ddalivery.entity.Category
import com.lyj.ddalivery.ddalivery.entity.Product
import com.lyj.ddalivery.ddalivery.entity.Seller
import com.thinkinglogic.builder.annotation.Builder
import javax.persistence.*

class ProductDto {
    data class Create(
            val name: String,
            var image: String,
            val description: String,
            val price: Int,
            val recommendedRateCount: Int,
            val recommendedUserCount: Int,
            val seller: Long,
            val category: Long
    ) {
        fun toEntity(seller: Seller, category: Category): Product = Product(null, name, image, description, price, recommendedRateCount, recommendedUserCount, seller, category)
    }


    data class ProductResponse(
            val productId: Long?,
            val productName: String,
            val image: String,
            val description: String,
            val price: Int,
            val recommendedRateCount: Int,
            val recommendedUserCount: Int,
            @JsonInclude(JsonInclude.Include.NON_NULL) // 널 요청시 제거
            var seller: SellerDto.Response? = null ,
            @JsonInclude(JsonInclude.Include.NON_NULL) // 널 요청시 제거
            var category: CategoryDto.Response? = null
    ){
        companion object{
            fun from(p : Product, relation: Array<String>?) : ProductResponse {
                val response = ProductResponse(p.productId, p.productName, p.image, p.description, p.price, p.recommendedRateCount, p.recommendedUserCount)

                relation?.let {
                    if (it.contains("category")) response.category = CategoryDto.Response.of(p.category,false)
                    if (it.contains("seller")) response.seller = SellerDto.Response.of(p.seller,false)
                }

                return response
            }
        }
    }



}