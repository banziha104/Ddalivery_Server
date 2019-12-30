package com.lyj.ddalivery.ddalivery.api.auth.repository

import com.lyj.ddalivery.ddalivery.api.auth.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

public interface AccountRepository : JpaRepository<Account,Long>{
    fun findByLoginId(loginId : String) : Account?
}