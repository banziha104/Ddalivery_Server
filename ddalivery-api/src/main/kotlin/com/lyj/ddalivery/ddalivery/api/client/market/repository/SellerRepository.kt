package com.lyj.ddalivery.ddalivery.api.client.market.repository

import com.lyj.ddalivery.ddalivery.entity.Seller
import org.springframework.data.jpa.repository.JpaRepository

interface SellerRepository : JpaRepository<Seller,Long>