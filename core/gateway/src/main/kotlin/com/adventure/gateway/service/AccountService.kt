package com.adventure.gateway.service

import com.adventure.apis.accounts.Commands.CreateAccount
import com.adventure.apis.accounts.Queries.DoesAccountExist

import com.adventure.apis.accounts.State
import com.adventure.apis.accounts.State.Role.BUYER
import com.adventure.apis.accounts.State.Role.SELLER
import com.adventure.gateway.controller.CompleteSignupRequest
import org.axonframework.commandhandling.CommandExecutionException
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.security.auth.login.AccountException

@Service
class AccountService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    fun registerAccount(request: CompleteSignupRequest): String {
        try {
            val principalId = UUID.randomUUID()
            logger.info("Handling request for principal $principalId")

            commandGateway.send<String>(
                CreateAccount(
                    accountId = principalId,
                    firstName = request.firstName,
                    lastName = request.lastName,
                    dateOfBirth = LocalDateTime.now(),
                    email = "principal.emailAddress@gmail.com",
                    gender = request.gender,
                    country = request.country,
                    role = SELLER
                )
            )

            return "Dear ${request.firstName}, Welcome to Soko!"
        } catch (ex: Exception) {
            // Log the exception for debugging purposes
            logger.error("Error registering account for ${request.firstName}", ex)

            // Wrap the exception in CommandExecutionException with custom message
            throw CommandExecutionException(
                "Failed to register account for ${request.firstName}",
                ex
            )
        }
    }

    @CommandHandler
    fun on(command: CreateAccount) {
        logger.info("$command")
    }

    fun accountExists(accountId: UUID): Boolean {
//        return queryGateway.query(
//            DoesAccountExist(accountId = accountId),
//            Boolean::class.java
//        ).get()
        TODO()
    }
}