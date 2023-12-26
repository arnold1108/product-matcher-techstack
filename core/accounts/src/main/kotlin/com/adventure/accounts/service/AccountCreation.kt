package com.adventure.accounts.service

import com.adventure.accounts.respository.BuyerRepository
import com.adventure.accounts.respository.SellerRepository
import com.adventure.apis.accounts.Commands.*
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class AccountCreation(
    private val buyerRepository: BuyerRepository,
    private val sellerRepository: SellerRepository
) {
    fun addBuyer(buyerId: UUID, details: UserDetails): Mono<String> {
        val buyerDetails = UserDetails(
            firstName = details.firstName,
            lastName = details.lastName,
            dob = details.dob,
            email = details.email,
            gender = details.gender,
            country = details.country
        )

        return Mono.fromCallable {
            buyerRepository.save(buyerDetails)
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
            country = details.country
        )

        return Mono.fromCallable {
            sellerRepository.save(sellerDetails)
            "Successfully added seller $sellerId"
        }
    }
}