package com.adventure.gateway.service

import com.adventure.apis.AccountNotFoundException
import com.adventure.apis.accounts.Commands
import com.adventure.apis.accounts.Requests.CreateAccountRequest
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class AccountService(
    private val command: ReactorCommandGateway,
    private val auth: AuthenticationService
) {

    fun registerUser(request: CreateAccountRequest): Mono<String> {
//        Check if the account exists

        val exists = auth.accountExists(request.emailAddress)

        val createBuyerAccountCommand = Commands.CreateAccount(
            userId = UUID.randomUUID(),
            firstName = request.firstName,
            lastName = request.lastName,
            dateOfBirth = request.dob,
            email = request.emailAddress,
            gender = request.gender,
            country = request.country,
            role = request.role
        )

        if (!exists) throw AccountNotFoundException(request.emailAddress)
        else {
            return command.send<ResponseEntity<String>>(createBuyerAccountCommand)
                .thenReturn("Welcome to Soko!")
        }

    }
}