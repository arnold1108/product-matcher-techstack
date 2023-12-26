package com.adventure.accounts.respository

import com.adventure.apis.accounts.Commands
import com.adventure.apis.accounts.Commands.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserAccountRepository: JpaRepository<UserDetails, UUID> {
    fun save(details: UserDetails): UserDetails
}