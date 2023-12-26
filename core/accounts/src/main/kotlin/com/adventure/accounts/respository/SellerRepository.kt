package com.adventure.accounts.respository


import com.adventure.accounts.entity.Seller
import com.adventure.apis.accounts.Commands.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SellerRepository: JpaRepository<Seller, UUID> {
    fun save(userId: UUID, details: UserDetails): Seller
}