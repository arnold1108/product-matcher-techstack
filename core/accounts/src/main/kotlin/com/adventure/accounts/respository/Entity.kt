package com.adventure.accounts.respository

import com.adventure.apis.accounts.Commands
import com.adventure.apis.accounts.Commands.UserDetails
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
class Repo {
    fun addBuyer(buyerId: UUID, details: UserDetails): Mono<String> {
        TODO()
    }
    fun addSeller(sellerId: UUID, details: UserDetails): Mono<String> {
        TODO()
    }
}