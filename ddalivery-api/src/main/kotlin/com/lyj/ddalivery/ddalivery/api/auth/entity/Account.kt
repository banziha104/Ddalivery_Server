package com.lyj.ddalivery.ddalivery.api.auth.entity

import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(
        @GeneratedValue(strategy = GenerationType.IDENTITY) @Id var accountId: Long?,
        @Column(nullable = false, unique = true) var loginId: String,
        @Column(nullable = false) var loginPassword: String,
        @Column(length = 15, nullable = false) var userName: String,
        @Column(length = 30, nullable = false) var address: String,
        @Column(length = 30, nullable = false) var detailAddress: String,
        @Column(length = 15, nullable = false) var phoneNumber: String,
        @Column(length = 10, nullable = false) var zipCode : Int
)