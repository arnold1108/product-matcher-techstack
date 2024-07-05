package com.adventure.accounts.command

import com.adventure.apis.accounts.Commands.CreateAccount
import com.adventure.apis.accounts.Commands.SuspendAccount
import com.adventure.apis.accounts.Events.AccountCreated
import com.adventure.apis.accounts.Events.AccountSuspended
import com.adventure.apis.accounts.State.*
import com.adventure.apis.accounts.State.AccountStatus.ACTIVE
import com.adventure.apis.accounts.State.AccountStatus.SUSPENDED
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventhandling.gateway.EventGateway
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import java.util.UUID

@Aggregate
class AccountAggregate() {

    @AggregateIdentifier
    private lateinit var accountId: UUID
    private lateinit var accountStatus: AccountStatus
    private lateinit var accountRole: Role
    private lateinit var eventGateway: EventGateway

    @CommandHandler
    constructor(command: CreateAccount): this() {
        eventGateway.publish(
            AccountCreated(
                accountId = command.accountId,
                accountStatus = ACTIVE,
                firstName = command.firstName,
                lastName = command.lastName,
                dateOfBirth = command.dateOfBirth,
                email = command.email,
                gender = command.gender,
                country = command.country,
                role = command.role
            )
        )
    }

    @EventSourcingHandler
    fun handle(event: AccountCreated){
        accountId = event.accountId
        accountStatus = ACTIVE
        accountRole =  event.role
    }

    @CommandHandler
    fun on(command: SuspendAccount) {
        if (accountStatus == ACTIVE) {
            eventGateway.publish(
                AccountSuspended(
                    accountId = command.accountId,
                    accountRole = accountRole
                )
            )
        }
    }

    @EventSourcingHandler
    fun handle(event: AccountSuspended){
        accountId = event.accountId
        accountStatus = SUSPENDED
    }

}