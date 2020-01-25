package com.lyj.ddalivery.ddalivery.api.client.controller

import com.lyj.ddalivery.ddalivery.api.client.dto.ProductDto
import com.lyj.ddalivery.ddalivery.api.client.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*


/***
 * 프로덕트 관련
 */

@RestController
@RequestMapping(value = ["/api/product"])
class ProductController @Autowired constructor(
        private val productService: ProductService
) {
    /***
     * 판매자 아이디로 조회
     * @param pageable Pageable 페이지 객체
     * @param seller Array<Long> 판매자 아이디
     * @return ApiResponse<*>
     */
    @GetMapping("seller")
    fun getPageBySeller(pageable: Pageable, @RequestParam("seller") seller : Array<Long>) = productService.getProductBySeller(pageable,seller)

    @GetMapping("location")
    fun getPageByLocation(pageable: Pageable,
                          @RequestParam("latitude") latitude : Double,
                          @RequestParam("longitude") longitude : Double,
                          @RequestParam("limit") limit : Int) = productService.getProductByLocation(pageable,latitude,longitude,limit)
    @PostMapping
    fun create(@RequestBody productDto: ProductDto.Create) = productService.createProduct(productDto)
}