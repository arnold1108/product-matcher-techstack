package com.adventure.gateway.controller

import com.adventure.apis.accounts.Commands
import com.adventure.apis.accounts.Commands.CreateBuyerAccount
import com.adventure.apis.accounts.State
import com.adventure.apis.accounts.State.Sex
import com.adventure.apis.marketplace.QueryResults
import com.adventure.apis.marketplace.QueryResults.ExploreProductsQueryResult
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class BuyerController(
    private val command: ReactorCommandGateway
) {
    @PostMapping("/buyer/account/create")
    fun createBuyerAccount(
        @RequestBody
        firstName: String,
        lastName: String,
        emailAddress: String,
        gender: Sex
    ): Mono<ResponseEntity<String>> {
        val buyerAccountCreateRequest = CreateBuyerAccount(
            buyerId = UUID.randomUUID(),
            firstName = firstName,
            lastName = lastName,
            email = emailAddress,
            gender = gender
        )
        return command.send(buyerAccountCreateRequest)
    }
    @GetMapping("/buyer/{buyerId}/explore")
    fun exploreProducts(@PathVariable buyerId: UUID): Mono<ResponseEntity<ExploreProductsQueryResult>> {
        TODO()
    }

}