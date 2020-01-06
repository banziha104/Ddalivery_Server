package com.lyj.ddalivery.ddalivery.api.client.market.service

import com.lyj.ddalivery.ddalivery.api.client.market.dto.ProductDto
import com.lyj.ddalivery.ddalivery.api.client.market.repository.ProductRepository
import com.lyj.ddalivery.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.config.ImagePathConfig
import com.lyj.ddalivery.ddalivery.entity.Product
import com.lyj.ddalivery.ddalivery.exception.product.FileNotSaveException
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermission
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Service
class ProductService @Autowired constructor(
        val productRepository: ProductRepository,
        val settings: ImagePathConfig
) {
    fun getProduct(pageable: Pageable): ApiResponse<*> = ApiResponseFactory.createOK(productRepository.findAll(pageable))

    fun createProduct(dto: ProductDto.Create) : ApiResponse<*> {
        println(settings)
        saveImage(dto)
        return ApiResponseFactory.DEFAULT_OK
    }


    fun saveImage(dto: ProductDto.Create) : ApiResponse<*>{
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
            val productImage = Paths.get(settings.productImagePath, today.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
            Files.createDirectories(productImage)
            Files.setPosixFilePermissions(productImage, permission)

            val builderFile = StringBuilder()
            val targetPath = Paths.get(productImage.toString(),
                    builderFile.append(dto.seller).append("_").append(today.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                            .append("_").append(dto.name).append(".jpg").toString())

            Files.write(targetPath, Base64.decodeBase64(dto.image))
            Files.setPosixFilePermissions(targetPath, permission)

            dto.image = StringUtils.substringAfter(targetPath.toString(),settings.productImagePath)
            productRepository.save(dto.toEntity())
            return ApiResponseFactory.DEFAULT_OK
        }catch (e : Exception){
            e.printStackTrace()
            return ApiResponseFactory.createException(FileNotSaveException())
        }

    }
}