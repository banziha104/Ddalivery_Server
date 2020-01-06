package com.lyj.ddalivery.ddalivery.api.auth.dto

import com.lyj.ddalivery.ddalivery.api.auth.entity.Account
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class AccountDto{
    data class Create(
            var loginId: String,
            var loginPassword: String,
            var name: String,
            var address: String,
            var detailAddress : String,
            var phoneNumber: String,
            var zipCode : Int){

        fun toEntity(passwordEncoder: PasswordEncoder) : Account{
            return Account(null,loginId, passwordEncoder.encode(loginPassword), name, address, detailAddress, phoneNumber, zipCode)
        }
    }
}