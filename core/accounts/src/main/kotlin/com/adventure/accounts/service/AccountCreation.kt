package com.adventure.accounts.service

import com.adventure.accounts.respository.UserAccountRepository
import com.adventure.apis.accounts.Commands
import com.adventure.apis.accounts.Commands.*
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class AccountCreation(
    private val userAccountRepository: UserAccountRepository
) {
    fun addBuyer(buyerId: UUID, details: UserDetails): Mono<String> {
        val buyerDetails = UserDetails(
            firstName = details.firstName,
            lastName = details.lastName,
            dob = details.dob,
            email = details.email,
            gender = details.gender,
            countryCode = details.countryCode
        )

        return Mono.fromCallable {
            userAccountRepository.save(buyerId, buyerDetails)
            "Successfully added buyer $buyerId"
        }
    }
    fun addSeller(sellerId: UUID, details: UserDetails): Mono<String> {
        val sellerDetails = UserDetails(
            firstName = details.firstName,
            lastName = details.lastName,
            dob = details.dob,
            email = details.email,
            gender = details.gender,
            countryCode = details.countryCode
        )

        return Mono.fromCallable {
            userAccountRepository.save(sellerId, sellerDetails)
            "Successfully added seller $sellerId"
        }
    }
}