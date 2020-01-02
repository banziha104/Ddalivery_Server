package com.lyj.ddalivery.ddalivery.core

import com.fasterxml.jackson.databind.ObjectMapper
import com.lyj.ddalivery.ddalivery.api.auth.dto.LoginDto
import com.lyj.ddalivery.ddalivery.api.auth.dto.TokenDto
import com.lyj.ddalivery.ddalivery.api.auth.service.AccountService
import com.lyj.ddalivery.ddalivery.api.auth.service.LoginService
import org.junit.Ignore
import org.junit.runner.RunWith
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
@ActiveProfiles("test")
@Ignore
class BaseControllerTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Autowired
    protected lateinit var modelMapper: ModelMapper

    @Autowired
    protected lateinit var loginService: LoginService

    @Throws(Exception::class)
    protected fun getBearerToken(needToCreateAccount: Boolean): String? {
        return "Bearer " + getAccessToken(needToCreateAccount)
    }


    @Throws(Exception::class)
    protected fun getAccessToken(needToCreateAccount: Boolean): String {

        val loginDto: LoginDto = LoginDto("banziha104@gmail.com","password");
        // Expect
        val dto: TokenDto = loginService!!.createUserToken(loginDto) as TokenDto
        return dto.token
    }
}