package com.adventure.gateway.controller

import com.adventure.apis.accounts.Commands
import com.adventure.apis.accounts.Requests.*
import com.adventure.gateway.utils.Mappings.ACCOUNT_CREATION_MAPPING
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class Account (private val command: ReactorCommandGateway){
    @PostMapping(ACCOUNT_CREATION_MAPPING)
    fun createBuyerAccount( @RequestBody request: CreateAccountRequest): Mono<ResponseEntity<String>> {
        val createBuyerAccountCommand = Commands.CreateAccount(
            userId = UUID.randomUUID(),
            firstName = request.firstName,
            lastName = request.lastName,
            dateOfBirth = request.dob,
            email = request.email,
            gender = request.gender,
            country = request.country,
            role = request.role
        )
        return command.send<ResponseEntity<String>?>(createBuyerAccountCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Welcome to Soko!"))
    }

}