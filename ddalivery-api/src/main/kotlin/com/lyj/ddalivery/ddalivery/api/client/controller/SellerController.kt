package com.lyj.ddalivery.ddalivery.api.client.controller

import com.lyj.ddalivery.ddalivery.api.client.dto.SellerDto
import com.lyj.ddalivery.ddalivery.api.client.service.SellerService
import com.lyj.ddalivery.ddalivery.entity.Seller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/seller"])
class SellerController @Autowired constructor(
        val sellerService: SellerService
) {

    @GetMapping
    fun findByDistance(@RequestParam("latitude") latitude : Double,
                       @RequestParam("longitude") longitude : Double,
                       @RequestParam("limit") limit : Int) = sellerService.findByDistance(latitude,longitude,limit)

    @PostMapping
    fun createSeller(@RequestBody dto : SellerDto.Create) = sellerService.create(dto)

}