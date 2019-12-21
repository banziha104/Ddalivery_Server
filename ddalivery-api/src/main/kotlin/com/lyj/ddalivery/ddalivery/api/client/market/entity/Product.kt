package com.lyj.ddalivery.ddalivery.api.client.market.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class Product(
        @GeneratedValue @Id var productId : Long,
        @Column var productName : String
)