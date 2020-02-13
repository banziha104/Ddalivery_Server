package com.lyj.ddalivery.ddalivery.api.client.repository

import com.lyj.ddalivery.ddalivery.entity.OrderGroup
import com.querydsl.core.types.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderGroupRepository : JpaRepository<OrderGroup,Long>{

}