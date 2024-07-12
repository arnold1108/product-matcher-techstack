package com.adventure.accounts.command

import com.adventure.apis.accounts.Commands.CreateAccount
import com.adventure.apis.accounts.Commands.SuspendAccount
import com.adventure.apis.accounts.Events.AccountCreated
import com.adventure.apis.accounts.Events.AccountSuspended
import com.adventure.apis.accounts.State.*
import com.adventure.apis.accounts.State.AccountStatus.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventhandling.gateway.EventGateway
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.util.UUID

@Aggregate
class AccountAggregate() {

    @AggregateIdentifier
    private lateinit var accountId: UUID
    private var accountStatus: AccountStatus = INACTIVE
    private lateinit var accountRole: Role
    private val logger = LoggerFactory.getLogger(this::class.java)

    @CommandHandler
    constructor(command: CreateAccount) : this() {
        logger.info("Handling command $command")
        val event = AccountCreated(
            accountId = command.accountId,
            accountStatus = ACTIVE,
            firstName = command.firstName,
            lastName = command.lastName,
//                dateOfBirth = command.dateOfBirth,
            email = command.email,
            gender = command.gender,
            country = command.country,
            role = command.role
        )

        AggregateLifecycle.apply(event)
        logger.info("Published event ::  $event")
    }

    @EventSourcingHandler
    fun handle(event: AccountCreated) {
        logger.info("Updated account status to $ACTIVE")
        accountId = event.accountId
        accountStatus = ACTIVE
        accountRole = event.role
    }

    @CommandHandler
    fun on(command: SuspendAccount) {
        if (accountStatus == ACTIVE) {
            AggregateLifecycle.apply(
                AccountSuspended(
                    accountId = command.accountId,
                    accountStatus = accountStatus
                )
            )
        }
    }

    @EventSourcingHandler
    fun handle(event: AccountSuspended) {
        accountId = event.accountId
        accountStatus = SUSPENDED
    }

}