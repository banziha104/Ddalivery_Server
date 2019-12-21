package com.lyj.ddalivery.ddalivery.entity

import javax.persistence.*


@Entity
data class Order(
        @GeneratedValue(strategy = GenerationType.IDENTITY) @Id val orderId : Long,
        @Column var clientId : Long, // 주문자명
        @Column var orderid : Long // 아이템명
) 
