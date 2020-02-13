package com.lyj.ddalivery.ddalivery.api.client.repository

import com.lyj.ddalivery.ddalivery.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order,Long>