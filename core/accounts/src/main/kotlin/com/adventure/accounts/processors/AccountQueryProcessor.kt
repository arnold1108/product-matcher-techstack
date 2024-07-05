package com.adventure.accounts.processors

import com.adventure.accounts.query.AccountRepository
import com.adventure.apis.accounts.Queries.DoesAccountExist
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class AccountQueryProcessor(private val accountRepository: AccountRepository) {

    @QueryHandler
    fun answer(query: DoesAccountExist): Boolean {
        return accountRepository.existsById(query.accountId)
    }
}