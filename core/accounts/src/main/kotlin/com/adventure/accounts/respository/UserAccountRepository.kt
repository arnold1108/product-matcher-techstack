package com.adventure.accounts.respository


import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserAccountRepository: JpaRepository<UserDetails, UUID> {
    fun save(userId: UUID, details: UserDetails): UserDetails
}