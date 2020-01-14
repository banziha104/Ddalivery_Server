package com.lyj.ddalivery.ddalivery.api.client.repository

import com.lyj.ddalivery.ddalivery.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category,Long>