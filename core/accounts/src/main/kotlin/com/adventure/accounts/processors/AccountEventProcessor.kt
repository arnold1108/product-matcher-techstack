package com.adventure.accounts.processors

import com.adventure.apis.accounts.Events.AccountCreated
import com.adventure.apis.accounts.Events.AccountSuspended
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service

@Service
class AccountEventProcessor {

    @EventHandler
    fun handle(event: AccountCreated) {
        TODO()
    }

    @EventHandler
    fun handle(event: AccountSuspended) {
        TODO()
    }
}