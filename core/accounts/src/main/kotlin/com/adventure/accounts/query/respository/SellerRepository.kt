package com.adventure.accounts.respository


import com.adventure.accounts.entity.Seller
import com.adventure.apis.accounts.Commands
import com.adventure.apis.accounts.Commands.CreateSellerAccount
import com.adventure.apis.accounts.Commands.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SellerRepository: JpaRepository<Seller, UUID> {
    fun save(details: CreateSellerAccount): Seller
}