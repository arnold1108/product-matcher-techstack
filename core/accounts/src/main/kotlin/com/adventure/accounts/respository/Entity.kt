package com.adventure.accounts.respository

import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class Repo {
    fun addBuyer(): Mono<String> {
        TODO()
    }
    fun addSeller(): Mono<String> {
        TODO()
    }
}