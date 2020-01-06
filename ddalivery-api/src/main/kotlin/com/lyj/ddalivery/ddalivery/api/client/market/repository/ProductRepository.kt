package com.lyj.ddalivery.ddalivery.api.client.market.repository

import com.lyj.ddalivery.ddalivery.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product,Long>{

}