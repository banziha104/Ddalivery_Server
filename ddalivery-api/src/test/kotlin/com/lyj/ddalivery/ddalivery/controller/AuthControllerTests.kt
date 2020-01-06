package com.lyj.ddalivery.ddalivery.controller

import com.lyj.ddalivery.ddalivery.api.auth.dto.AccountDto
import com.lyj.ddalivery.ddalivery.api.auth.dto.LoginDto
import com.lyj.ddalivery.ddalivery.core.BaseControllerTest
import com.lyj.ddalivery.ddalivery.utils.`null`
import com.lyj.ddalivery.ddalivery.utils.number
import com.lyj.ddalivery.ddalivery.utils.string
import org.junit.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class AuthControllerTests : BaseControllerTest() {

    @Test
    @Throws(Exception::class)
    fun signUp() {
        val request = AccountDto.Create("banziha104@gmail.com", "password","lyj","buchon","1313","01011112222",131)

        val result = mockMvc!!.perform(
                post("/api/account/signup")
//                        .header(HttpHeaders.AUTHORIZATION, getBearerToken(false))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print())

        result.andExpect(status().isOk)
                .andDo(print())
                .andDo(document("account/signUp",
                        requestFields(
                                fieldWithPath("loginId").string().description("로그인 아이디"),
                                fieldWithPath("loginPassword").string().description("로그인 패스워드"),
                                fieldWithPath("name").string().description("이름"),
                                fieldWithPath("address").string().description("주소"),
                                fieldWithPath("detailAddress").string().description("상세주소"),
                                fieldWithPath("phoneNumber").string().description("전화번호"),
                                fieldWithPath("zipCode").number().description("우편주소")
                        ),
                        responseFields(
                                fieldWithPath("code").string().description("응답코드"),
                                fieldWithPath("message").string().description("메세지"),
                                fieldWithPath("data").`null`().description("데이터")
                        )
                ))

    }

    @Test
    @Throws(Exception::class)
    fun login() {
        val request = LoginDto("banziha104@gmail.com", "password")

        val result = mockMvc!!.perform(
                post("/api/account/login")
//                        .header(HttpHeaders.AUTHORIZATION, getBearerToken(false))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print())
        result.andExpect(status().isOk)
                .andDo(print())
                .andDo(document("account/login",
                        requestFields(
                                fieldWithPath("loginId").string().description("로그인 아이디"),
                                fieldWithPath("loginPassword").string().description("로그인 패스워드")
                        ),
                        responseFields(
                                beneathPath("data").withSubsectionId("data"),
                                fieldWithPath("token").string().description("토큰")
                        )

                ))
    }


}
