package com.adventure.gateway.controller

import com.adventure.apis.accounts.Commands
import com.adventure.apis.accounts.Requests.*
import com.adventure.gateway.service.AccountService
import com.adventure.gateway.utils.Mappings.ACCOUNT_CREATION_MAPPING
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class Account (private val account: AccountService){
    @PostMapping(ACCOUNT_CREATION_MAPPING)
    fun createBuyerAccount( @RequestBody request: CreateAccountRequest): Mono<ResponseEntity<String>>  {

        return account.registerUser(request)
            .map { ResponseEntity.ok(it) }

    }
}