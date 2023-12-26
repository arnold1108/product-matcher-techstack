package com.adventure.accounts.respository

import com.adventure.accounts.entity.Buyer
import com.adventure.apis.accounts.Commands
import com.adventure.apis.accounts.Commands.CreateBuyerAccount
import com.adventure.apis.accounts.Commands.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BuyerRepository: JpaRepository<Buyer, Long> {
    fun save(details: CreateBuyerAccount): Buyer
}
