package com.adventure.accounts.integration

import com.adventure.accounts.query.AccountEntity
import com.adventure.accounts.query.AccountRepository
import com.adventure.apis.accounts.Events
import com.adventure.apis.accounts.Events.AccountCreated
import com.adventure.apis.accounts.Queries
import com.adventure.apis.accounts.Queries.DoesAccountExist
import com.adventure.apis.accounts.State
import com.adventure.apis.accounts.State.AccountStatus.ACTIVE
import com.adventure.apis.accounts.State.Role.SELLER
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.gateway.EventGateway
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringBootTest
@ContextConfiguration(initializers = [TestContainerInitializer::class])
@ActiveProfiles("test")
class AccountEventProcessorTest(

) {
    @Autowired
    private lateinit var eventGateway: EventGateway

    @Autowired
    private lateinit var queryGateway: QueryGateway

    @Test
    fun `test AccountCreated EventHandler`() {

        eventGateway.publish(accountCreatedEvent)
        Thread.sleep(500)
        assertTrue {
            queryGateway.query(
                DoesAccountExist(accountId = accountId),
                Boolean::class.java
            ).get()
        }
    }

    companion object {
        private val accountId = UUID.randomUUID()
        private val firstName = "Arnold"
        private val lastName = "Opiyo"
        private val dateOfBirth = LocalDateTime.now()
        private val accountStatus = ACTIVE
        private val emailAddress = "rnldlinus@gmail.com"
        private val gender = "Male"
        private val country = "Kenya"
        private val role = SELLER
        private val accountCreatedEvent =
            AccountCreated(
                accountId = accountId,
                firstName = firstName,
                lastName = lastName,
                dateOfBirth = dateOfBirth,
                accountStatus = accountStatus,
                email = emailAddress,
                gender = gender,
                country = country,
                role = role
            )
    }
}
