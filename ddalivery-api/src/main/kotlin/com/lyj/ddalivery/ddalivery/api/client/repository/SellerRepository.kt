package com.lyj.ddalivery.ddalivery.api.client.repository

import com.lyj.ddalivery.ddalivery.entity.Seller
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SellerRepository : JpaRepository<Seller,Long>{

    @Query("select * from seller s where st_distance_sphere(POINT(:longitude,:latitude),POINT(s.longitude,s.latitude)) < :limit",nativeQuery = true)
    fun findAllByDistance(@Param("latitude") latitude : Double,
                          @Param("longitude") longitude : Double,
                          @Param("limit") limit : Int) : List<Seller>
}