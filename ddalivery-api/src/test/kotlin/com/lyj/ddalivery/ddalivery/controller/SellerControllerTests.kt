package com.lyj.ddalivery.ddalivery.controller

import com.lyj.ddalivery.utils.GeoUtils.wktToGeometry
import org.junit.Test

class SellerControllerTests{
    @Test
    fun abc(){
        val a = wktToGeometry(37.5,123.12)
        println(a)
    }
}