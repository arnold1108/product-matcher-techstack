package com.adventure.gateway.service

import com.adventure.apis.accounts.Commands.CreateAccount
import com.adventure.apis.accounts.Queries.DoesAccountExist
import com.adventure.apis.accounts.Requests.CompleteSignupRequest
import com.adventure.gateway.utils.SecurityUtils.extractPrincipalDetails
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*
import javax.security.auth.login.AccountException

@Service
class AccountService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {
    fun registerAccount(request: CompleteSignupRequest): String {
        val principal = extractPrincipalDetails(authentication = SecurityContextHolder.getContext().authentication)
        val exists = accountExists(accountId = principal.principalId)

        if (!exists) {
            commandGateway.send<Void>(
                CreateAccount(
                    accountId = principal.principalId,
                    firstName = request.firstName,
                    lastName = request.lastName,
                    dateOfBirth = request.dob,
                    email = principal.emailAddress,
                    gender = request.gender,
                    country = request.country,
                    role = principal.role
                )
            )
            return "Welcome to Soko!"
        } else {
            throw AccountException("${principal.emailAddress} Account already exists")
        }
    }

    fun accountExists(accountId: UUID): Boolean {
        return queryGateway.query(
            DoesAccountExist(accountId = accountId),
            Boolean::class.java
        ).get()
    }
}