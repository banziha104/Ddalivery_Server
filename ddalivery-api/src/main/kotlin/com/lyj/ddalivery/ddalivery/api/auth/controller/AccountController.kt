package com.lyj.ddalivery.ddalivery.api.auth.controller

import com.lyj.ddalivery.ddalivery.api.auth.dto.AccountDto
import com.lyj.ddalivery.ddalivery.api.auth.dto.LoginDto
import com.lyj.ddalivery.ddalivery.api.auth.dto.TokenDto
import com.lyj.ddalivery.ddalivery.api.auth.service.AccountService
import com.lyj.ddalivery.ddalivery.api.auth.service.LoginService
import com.lyj.ddalivery.ddalivery.api.response.ApiResponse
import com.lyj.ddalivery.ddalivery.api.response.ApiResponseFactory
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/account"])
@Slf4j
class AccountController @Autowired constructor(
        private val loginService: LoginService,
        private val accountService: AccountService
) {


    @PostMapping("signup")
    fun signUp(@RequestBody accountDto: AccountDto.Create) : ApiResponse<*> = accountService.signUp(accountDto)

    @PostMapping("login")
    fun login(@RequestBody loginDto: LoginDto) : ApiResponse<*> = loginService.createUserToken(loginDto)
}