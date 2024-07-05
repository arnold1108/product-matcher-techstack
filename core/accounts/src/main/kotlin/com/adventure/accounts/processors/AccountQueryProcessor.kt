package com.adventure.accounts.processors

import com.adventure.apis.accounts.Queries.DoesAccountExist
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class AccountQueryProcessor {

    @QueryHandler
    fun answer(query: DoesAccountExist): Boolean {
        TODO()
    }
}