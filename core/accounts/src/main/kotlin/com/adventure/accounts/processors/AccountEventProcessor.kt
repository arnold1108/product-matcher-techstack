package com.adventure.accounts.processors

import com.adventure.accounts.query.AccountEntity
import com.adventure.accounts.query.AccountRepository
import com.adventure.apis.accounts.Events.AccountCreated
import com.adventure.apis.accounts.Events.AccountSuspended
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class AccountEventProcessor(private val accountRepository: AccountRepository){

    @EventHandler
    fun handle(event: AccountCreated) {

        val accountEntity = AccountEntity.newAccount(
            sellerId = event.accountId,
            accountStatus = event.accountStatus,
            accountRole = event.role,
            firstName = event.firstName,
            lastName = event.lastName,
            dateOfBirth = event.dateOfBirth,
            email = event.email,
            gender = event.gender,
            country = event.country
        )
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
