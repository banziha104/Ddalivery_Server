package com.lyj.ddalivery.ddalivery.api.client.repository

import com.lyj.ddalivery.ddalivery.entity.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProductRepository : JpaRepository<Product,Long>{

    @Query("SELECT * FROM product p where p.seller_id IN :ids",nativeQuery = true)
    fun findAllBySellers(pageable: Pageable,@Param("ids") ids : Array<Long>) : List<Product>
}