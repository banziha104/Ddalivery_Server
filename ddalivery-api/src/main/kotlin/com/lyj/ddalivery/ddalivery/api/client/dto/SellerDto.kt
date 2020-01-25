package com.lyj.ddalivery.ddalivery.api.client.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.lyj.ddalivery.ddalivery.entity.Product
import com.lyj.ddalivery.ddalivery.entity.Seller
import com.vividsolutions.jts.geom.Point
import javax.persistence.OneToMany

class SellerDto {
    data class Create
    (
            val sellerName: String,
            val image: String,
            val address: String,
            val lat: Double,
            val long: Double
    ) {
        fun toEntity(imageUrl: String): Seller {
            return Seller(null, sellerName, imageUrl, address,lat, long)
        }
    }


    data class Response(
            val sellerId: Long?,
            val sellerName: String,
            val image: String,
            val address : String,
            val latitude: Double,
            val longitude: Double,

            @JsonInclude(JsonInclude.Include.NON_NULL) // 널 요청시 제거
            var products: List<Product>? = null
    ) {
        companion object {
            fun of(s: Seller, isRelation: Boolean): Response =
                    if (isRelation) Response(s.sellerId, s.sellerName, s.image,s.address, s.latitude, s.longitude, s.products)
                    else Response(s.sellerId, s.sellerName, s.image,s.address, s.latitude, s.longitude)
        }
    }
}