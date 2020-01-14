package com.lyj.ddalivery.ddalivery.api.auth.service

import com.lyj.ddalivery.ddalivery.api.auth.dto.AccountDto
import com.lyj.ddalivery.ddalivery.api.auth.repository.AccountRepository
import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.entity.Seller
import com.lyj.ddalivery.ddalivery.exception.auth.LoginIdDuplicateException
import com.vividsolutions.jts.geom.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService @Autowired constructor(
        val accountRepository: AccountRepository,
        val passwordEncoder: PasswordEncoder
) {

    fun signUp(dto: AccountDto.Create): ApiResponse<*> {
        val result = accountRepository.findByLoginId(dto.loginId)
        result?.let {
            return ApiResponseFactory.createException(LoginIdDuplicateException())
        }.let {
            accountRepository.save(dto.toEntity(passwordEncoder))
            return ApiResponseFactory.DEFAULT_OK
        }
    }
}