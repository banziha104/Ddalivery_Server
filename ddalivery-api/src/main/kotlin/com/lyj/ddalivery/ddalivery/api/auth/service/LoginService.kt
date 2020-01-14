package com.lyj.ddalivery.ddalivery.api.auth.service

import com.lyj.ddalivery.ddalivery.api.auth.dto.LoginDto
import com.lyj.ddalivery.ddalivery.api.auth.dto.TokenDto
import com.lyj.ddalivery.ddalivery.api.auth.repository.AccountRepository
import com.lyj.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.api.response.ApiResponseFactory
import com.lyj.ddalivery.ddalivery.exception.auth.PasswordInCorrectException
import com.lyj.ddalivery.ddalivery.exception.auth.UserResourceNotFoundException
import com.lyj.ddalivery.jwt.JWTSession
import com.lyj.ddalivery.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoginService @Autowired constructor(
        private val accountRepository: AccountRepository,
        private val jwtTokenProvider: JwtTokenProvider,
        private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun createUserToken(dto: LoginDto): ApiResponse<*> {
        val result = accountRepository.findByLoginId(dto.loginId)

        result?.let {
            return if (passwordEncoder.matches(dto.loginPassword, it.loginPassword)) {
                val session = JWTSession(it.accountId, it.loginId, it.address, it.userName)
                ApiResponseFactory.createOK(TokenDto(jwtTokenProvider.createToken(session)))
            } else {
                ApiResponseFactory.createException(PasswordInCorrectException())
            }
        }.let {
            return ApiResponseFactory.createException(UserResourceNotFoundException())
        }
    }
}
