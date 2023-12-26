package com.adventure.accounts.respository

import com.adventure.accounts.entity.Buyer
import com.adventure.apis.accounts.Commands.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BuyerRepository: JpaRepository<Buyer, Long> {
    fun save(details: UserDetails): Buyer
}
