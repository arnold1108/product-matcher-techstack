package com.adventure.accounts.processors

import com.adventure.accounts.query.AccountEntity
import com.adventure.accounts.query.AccountRepository
import com.adventure.apis.accounts.Events.AccountCreated
import com.adventure.apis.accounts.Events.AccountSuspended
import com.adventure.apis.accounts.State
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class AccountEventProcessor(private val accountRepository: AccountRepository){
    private val logger = LoggerFactory.getLogger(this::class.java)

    @EventHandler
    fun handle(event: AccountCreated) {

        val accountEntity = AccountEntity.newAccount(
            sellerId = event.accountId,
            accountStatus = event.accountStatus,
            accountRole = State.Role.BUYER,
            firstName = event.firstName,
            lastName = event.lastName,
            dateOfBirth = event.dateOfBirth,
            email = event.email,
            gender = event.gender,
            country = event.country
        )

        logger.info("Persisting entity $accountEntity")
        accountRepository.save(accountEntity)
    }

    @EventHandler
    fun handle(event: AccountSuspended) {
        val accountEntity =
            accountRepository.findById(event.accountId)
                .getOrElse { throw NoSuchElementException("Account Not Found") }

        accountEntity.updateAccountStatus(accountStatus = event.accountStatus)
        accountRepository.save(accountEntity)
    }
}
