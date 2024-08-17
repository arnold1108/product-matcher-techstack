package com.adventure.accounts.unit

import com.adventure.accounts.command.AccountAggregate
import com.adventure.apis.accounts.Commands.CreateAccount
import com.adventure.apis.accounts.Commands.SuspendAccount
import com.adventure.apis.accounts.Events.AccountCreated
import com.adventure.apis.accounts.Events.AccountSuspended
import com.adventure.apis.accounts.State.AccountStatus.ACTIVE
import com.adventure.apis.accounts.State.AccountStatus.SUSPENDED
import com.adventure.apis.accounts.State.Role.SELLER
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertEquals


class AccountAggregateTest {

    private lateinit var aggregateFixture: AggregateTestFixture<AccountAggregate>
    private val createAccountCommand = CreateAccount(
        accountId = UUID.randomUUID(),
        firstName = "Arnold",
        lastName = "Opiyo",
        email = "arnoldodhiambo@gmail.com",
        country = "Kenya",
        gender = "MALE",
        dateOfBirth = LocalDateTime.now(),
        role = SELLER
    )
    private val accountCreatedEvent = AccountCreated(
        accountId = createAccountCommand.accountId,
        firstName = createAccountCommand.firstName,
        lastName = createAccountCommand.lastName,
        email = createAccountCommand.email,
        country = createAccountCommand.country,
        gender =  createAccountCommand.gender,
        role = createAccountCommand.role,
        dateOfBirth = createAccountCommand.dateOfBirth,
        accountStatus = ACTIVE
    )
    private val suspendAccountCommand = SuspendAccount(
        accountId = accountCreatedEvent.accountId
    )
    private val accountSuspendedEvent = AccountSuspended(
        accountStatus = SUSPENDED,
        accountId = suspendAccountCommand.accountId
    )


    @BeforeEach
    fun setup() {
        aggregateFixture = AggregateTestFixture(AccountAggregate::class.java)
    }

    @Test
    fun `test CreateAccount command`() {
        aggregateFixture
            .givenNoPriorActivity()
            .`when`(createAccountCommand)
            .expectSuccessfulHandlerExecution()
            .expectEvents(accountCreatedEvent)
            .expectState { assertEquals(it.accountStatus, ACTIVE) }
    }

    @Test
    fun `test SuspendAccount command`() {
        aggregateFixture
            .given(accountCreatedEvent)
            .`when`(suspendAccountCommand)
            .expectSuccessfulHandlerExecution()
            .expectEvents(accountSuspendedEvent)
            .expectState { assertEquals(it.accountStatus, SUSPENDED) }
    }
}