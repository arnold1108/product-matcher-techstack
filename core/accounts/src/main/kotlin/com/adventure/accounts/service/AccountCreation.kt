package com.adventure.accounts.service

import com.adventure.apis.accounts.Commands
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class AccountCreation {
    fun addBuyer(buyerId: UUID, details: Commands.UserDetails): Mono<String> {
        TODO()
    }
    fun addSeller(sellerId: UUID, details: Commands.UserDetails): Mono<String> {
        TODO()
    }
}