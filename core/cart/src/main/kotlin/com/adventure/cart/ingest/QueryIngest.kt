package com.adventure.cart.ingest

import com.adventure.apis.cart.Queries
import com.adventure.apis.cart.Queries.ViewCart
import org.axonframework.queryhandling.QueryHandler

class QueryIngest {
    @QueryHandler
    fun handleQuery(query: ViewCart) {
        TODO()
    }

}