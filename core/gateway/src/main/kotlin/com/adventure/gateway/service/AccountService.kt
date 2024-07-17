package com.adventure.gateway.service

import com.adventure.apis.accounts.Commands.CreateAccount
import com.adventure.apis.accounts.State.Role.SELLER
import com.adventure.gateway.controller.CompleteSignupRequest
import org.axonframework.commandhandling.CommandExecutionException
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    fun registerAccount(request: CompleteSignupRequest): String {
        try {
            val principalId = UUID.randomUUID()
            val command = CreateAccount(
                accountId = principalId,
                firstName = request.firstName,
                lastName = request.lastName,
//                    dateOfBirth = LocalDateTime.now(),
                email = "principal.emailAddress@gmail.com",
                gender = request.gender,
                country = request.country,
                role = SELLER
            )
            logger.info("Handling request for principal $principalId")
            logger.info("Handling command ::  $command")

            commandGateway.send<String>(command)

            return "Dear ${request.firstName}, Welcome to Soko!"
        } catch (ex: Exception) {
            logger.error("Error registering account for ${request.firstName}", ex)
            throw CommandExecutionException("Failed to register account for ${request.firstName}",
                ex
            )
        }
    }


    fun accountExists(accountId: UUID): Boolean {
//        return queryGateway.query(
//            DoesAccountExist(accountId = accountId),
//            Boolean::class.java
//        ).get()
        TODO()
    }
}