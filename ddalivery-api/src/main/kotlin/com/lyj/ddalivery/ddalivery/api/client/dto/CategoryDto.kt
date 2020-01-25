package com.lyj.ddalivery.ddalivery.api.client.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.lyj.ddalivery.ddalivery.entity.Category
import com.lyj.ddalivery.ddalivery.entity.Product

class CategoryDto{

    data class Response(
            val categoryId : Long?,
            val categoryName : String,
            val image : String?,
            @JsonInclude(JsonInclude.Include.NON_NULL) // 널 요청시 제거
            val products : List<Product>? = null
    ){
        companion object{
            fun of(c : Category, isRelation : Boolean) : Response =
                    if (isRelation) Response(c.categoryId,c.categoryName,c.image,c.products)
                    else Response(c.categoryId,c.categoryName,c.image)

        }
    }
}