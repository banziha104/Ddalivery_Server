package com.lyj.ddalivery.ddalivery.api.client.service.impl

import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.api.client.dto.ProductDto
import com.lyj.ddalivery.ddalivery.api.client.repository.CategoryRepository
import com.lyj.ddalivery.ddalivery.api.client.repository.ProductRepository
import com.lyj.ddalivery.ddalivery.api.client.repository.SellerRepository
import com.lyj.ddalivery.ddalivery.api.client.service.ProductService
import com.lyj.ddalivery.ddalivery.config.ImagePathConfig
import com.lyj.ddalivery.ddalivery.exception.product.CategoryNotFoundException
import com.lyj.ddalivery.ddalivery.exception.product.FileNotSaveException
import com.lyj.ddalivery.ddalivery.exception.product.SellerNotFoundException
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermission
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.HashSet
import javax.persistence.EntityManager

@Service
class ProductServiceImpl @Autowired constructor(
        val productRepository: ProductRepository,
        val sellerRepository: SellerRepository,
        val categoryRepository: CategoryRepository,
        val entityManager: EntityManager,
        val settings: ImagePathConfig
) : ProductService {


    /***
     * 판매자 아이디를 통한 조회
     * @param pageable Pageable
     * @param seller Array<Long>
     * @return ApiResponse<*>
     */
    override fun getProductBySeller(pageable: Pageable, seller: Array<Long>): ApiResponse<*> = ApiResponseFactory.createOK(
            productRepository
                    .findAllBySellers(pageable, seller)
                    .map { ProductDto.ProductResponse.from(it, arrayOf("category", "seller")) }
    )

    override fun getAllProduct(): ApiResponse<*> = ApiResponseFactory.createOK(productRepository.findAll().map { ProductDto.ProductResponse.from(it, arrayOf("category","seller")) })


    /***
     * 위치를 이용한 조회
     * @param pageable Pageable
     * @param latitude Double
     * @param longitude Double
     * @param limit Int
     * @return ApiResponse<*>
     */
    override fun getProductByLocation(pageable: Pageable, latitude: Double, longitude: Double, limit: Int): ApiResponse<*> {
        val seller = sellerRepository.findAllByDistance(latitude, longitude, limit).map { it.sellerId!! }.toTypedArray()
        return if (seller.isEmpty()) ApiResponseFactory.createException(SellerNotFoundException())
        else ApiResponseFactory.createOK(productRepository
                .findAllBySellers(pageable, seller)
                .map { ProductDto.ProductResponse.from(it, arrayOf("category", "seller")) })
    }


    @Transactional
    override fun createProduct(dto: ProductDto.Create): ApiResponse<*> {
        saveImage(dto)
        return ApiResponseFactory.DEFAULT_OK
    }


    fun saveImage(dto: ProductDto.Create): ApiResponse<*> {
        val today = LocalDateTime.now()
        val permission = HashSet<PosixFilePermission>()
        //Adding owner's file permissions
        permission.add(PosixFilePermission.OWNER_EXECUTE)
        permission.add(PosixFilePermission.OWNER_READ)
        permission.add(PosixFilePermission.OWNER_WRITE)
        permission.add(PosixFilePermission.GROUP_EXECUTE)
        permission.add(PosixFilePermission.GROUP_READ)
        permission.add(PosixFilePermission.OTHERS_EXECUTE)
        permission.add(PosixFilePermission.OTHERS_READ)
        permission.add(PosixFilePermission.OTHERS_WRITE)

        try {
            val productImage = Paths.get(settings.imagePath, "product", today.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
            Files.createDirectories(productImage)
            Files.setPosixFilePermissions(productImage, permission)

            val builderFile = StringBuilder()
            val targetPath = Paths.get(productImage.toString(),
                    builderFile.append(dto.seller).append("_").append(today.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                            .append("_").append(".jpg").toString())

            Files.write(targetPath, Base64.decodeBase64(dto.image))
            Files.setPosixFilePermissions(targetPath, permission)

            dto.image = StringUtils.substringAfter(targetPath.toString(), settings.imagePath)

            productRepository.save(dto.toEntity(
                    sellerRepository.findById(dto.seller).orElseThrow(::SellerNotFoundException),
                    categoryRepository.findById(dto.category).orElseThrow(::CategoryNotFoundException)
            ))
            return ApiResponseFactory.DEFAULT_OK
        } catch (e: SellerNotFoundException) {
            e.printStackTrace()
            return ApiResponseFactory.createException(SellerNotFoundException())
        } catch (e: CategoryNotFoundException) {
            e.printStackTrace()
            return ApiResponseFactory.createException(CategoryNotFoundException())
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponseFactory.createException(FileNotSaveException())
        }
    }
}